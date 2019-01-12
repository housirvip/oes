package vip.housir.user.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.housir.base.constant.Constant;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.TradeDto;
import vip.housir.base.dto.UserDto;
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
import java.util.Optional;

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
    private String initRole;

    @Value("${user.group}")
    private String initGroup;

    @Value("${user.level}")
    private Integer initLevel;

    @Value("${user.coin}")
    private Integer initCoin;

    @Override
    public String login(UserDto userDto) {

        User user = userMapper.selectByAccount(userDto.getAccount());
        //账户未找到
        Preconditions.checkNotNull(user, ErrorMessage.ACCOUNT_NOT_FOUND);
        //账户被封禁
        Preconditions.checkArgument(user.getEnable(), ErrorMessage.ACCOUNT_DISABLED);
        //验证密码
        Preconditions.checkArgument(passwordEncoder.matches(userDto.getPassword(), user.getPassword()), ErrorMessage.ACCOUNT_PASSWORD_ERROR);

        return jwtUtils.encode(user.getId(), user.getRole());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String register(UserDto userDto) {

        // 判断账户是否已经存在
        List<String> check = this.checkExist(userDto);
        Preconditions.checkArgument(check.size() == 0, check.toString());

        User user = new User();
        user.setCreateTime(new Date());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPhone(userDto.getPhone());
        user.setGroup(initGroup);
        user.setLevel(initLevel);
        user.setRole(Lists.newArrayList(Constant.ROLE_PREFIX + initRole));
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

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
    public String refresh(Integer uid) {

        User user = userMapper.selectByPrimaryKey(uid);

        return jwtUtils.encode(uid, user.getRole());
    }

    @Override
    public User one(Integer uid) {

        User user = userMapper.selectByPrimaryKey(uid);
        user.setPassword(null);

        return user;
    }

    @Override
    public UserInfo info(Integer uid) {

        return userInfoMapper.selectByUid(uid);
    }

    @Override
    public User detail(Integer uid) {

        User user = userMapper.selectByPrimaryKey(uid);
        user.setUserInfo(userInfoMapper.selectByUid(uid));
        user.setWallet(walletMapper.selectByUid(uid));
        user.setPassword(null);

        return user;
    }

    @Override
    public Page<User> pageByParam(PageDto pageDto) {

        Page<User> userPage = userMapper.listByParam(pageDto.putParam().getParamAsMap());
        if (userPage.getTotal() == 0) {
            return userPage;
        }

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

    @Override
    public Boolean levelUp(TradeDto tradeDto) {

        User user = userMapper.selectByPrimaryKey(tradeDto.getUid());
        Preconditions.checkNotNull(user, ErrorMessage.USER_NOT_FOUND);

        Optional.ofNullable(tradeDto.getLevelTo())
                .ifPresent(user::setLevel);
        Optional.ofNullable(tradeDto.getLevelUp())
                .ifPresent(up -> user.setLevel(user.getLevel() + up));

        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Override
    public Boolean update(UserInfo userInfo) {

        return userInfoMapper.updateByPrimaryKeySelective(userInfo) > 0;
    }

    private List<String> checkExist(UserDto userDto) {

        List<String> result = Lists.newArrayList();
        if (BooleanUtils.isTrue(userMapper.existUsername(userDto.getUsername()))) {
            result.add(ErrorMessage.USERNAME_EXIST);
        }
        if (BooleanUtils.isTrue(userMapper.existEmail(userDto.getEmail()))) {
            result.add(ErrorMessage.EMAIL_EXIST);
        }
        if (BooleanUtils.isTrue(userMapper.existPhone(userDto.getPhone()))) {
            result.add(ErrorMessage.PHONE_EXIST);
        }

        return result;
    }
}
