package vip.housir.user.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.housir.base.constant.Constant;
import vip.housir.base.request.PageRequest;
import vip.housir.base.request.UserRequest;
import vip.housir.base.response.ErrorMessage;
import vip.housir.base.utils.JwtUtils;
import vip.housir.user.entity.User;
import vip.housir.user.entity.UserInfo;
import vip.housir.user.entity.Wallet;
import vip.housir.user.mapper.UserInfoMapper;
import vip.housir.user.mapper.UserMapper;
import vip.housir.user.mapper.WalletMapper;
import vip.housir.user.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;
    private final WalletMapper walletMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;


    @Value("${user.role}")
    private String[] initRole;

    @Value("${user.group}")
    private String initGroup;

    @Value("${user.level}")
    private Integer initLevel;

    @Value("${user.coin}")
    private Integer initCoin;

    @Override
    public String login(UserRequest userRequest) {

        User user = userMapper.selectByAccount(userRequest.getAccount());
        //账户未找到
        Preconditions.checkNotNull(user, ErrorMessage.ACCOUNT_NOT_FOUND);
        //账户被封禁
        Preconditions.checkArgument(user.getEnable(), ErrorMessage.ACCOUNT_DISABLED);
        //验证密码
        Preconditions.checkArgument(passwordEncoder.matches(userRequest.getPassword(), user.getPassword()), ErrorMessage.ACCOUNT_PASSWORD_ERROR);

        return jwtUtils.encode(user.getId(), user.getRole());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String register(UserRequest userRequest) {

        // 判断账户是否已经存在
        List<String> check = this.checkExist(userRequest);
        Preconditions.checkArgument(check.size() == 0, check.toString());

        User user = new User();
        user.setCreateTime(new Date());
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPhone(userRequest.getPhone());
        user.setGroup(Constant.ROLE_PREFIX + initGroup);
        user.setLevel(initLevel);
        user.setRole(Lists.newArrayList(initRole));
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        userMapper.insertSelective(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getId());
        userInfo.setNickname(user.getUsername());

        userInfoMapper.insertSelective(userInfo);

        Wallet wallet = new Wallet();
        wallet.setUid(user.getId());
        wallet.setCoin(initCoin);

        walletMapper.insertSelective(wallet);

        return jwtUtils.encode(user.getId(), user.getRole());
    }

    @Override
    public User one() {

        Integer uid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userMapper.selectByPrimaryKey(uid);

        user.setPassword(null);

        return user;
    }

    @Override
    public User detail() {

        Integer uid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userMapper.selectByPrimaryKey(uid);
        user.setUserInfo(userInfoMapper.selectByUid(uid));
        user.setWallet(walletMapper.selectByUid(uid));
        user.setPassword(null);

        return user;
    }

    @Override
    public Page<User> pageByParam(PageRequest pageRequest) {

        Page<User> userPage = userMapper.listByParam(pageRequest.addParam().getMap());

        List<Integer> uids = Lists.newArrayList();
        userPage.forEach(item -> uids.add(item.getId()));

        Map<Integer, UserInfo> userInfoMap = userInfoMapper.listInUids(uids);
        Map<Integer, Wallet> walletMap = walletMapper.listInUids(uids);
        userPage.forEach(item -> {
            item.setUserInfo(userInfoMap.get(item.getId()));
            item.setWallet(walletMap.get(item.getId()));
            item.setPassword(null);
        });

        return userPage;
    }

    private List<String> checkExist(UserRequest userRequest) {

        List<String> result = Lists.newArrayList();
        if (BooleanUtils.isTrue(userMapper.existUsername(userRequest.getUsername()))) {
            result.add(ErrorMessage.USERNAME_EXIST);
        }
        if (BooleanUtils.isTrue(userMapper.existEmail(userRequest.getEmail()))) {
            result.add(ErrorMessage.EMAIL_EXIST);
        }
        if (BooleanUtils.isTrue(userMapper.existPhone(userRequest.getPhone()))) {
            result.add(ErrorMessage.PHONE_EXIST);
        }

        return result;
    }
}
