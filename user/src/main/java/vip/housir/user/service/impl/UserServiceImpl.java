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
import vip.housir.base.request.AuthRequest;
import vip.housir.base.response.ErrorMessage;
import vip.housir.user.entity.User;
import vip.housir.user.entity.UserInfo;
import vip.housir.user.mapper.UserInfoMapper;
import vip.housir.user.mapper.UserMapper;
import vip.housir.user.service.UserService;

import java.util.*;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserInfoMapper userInfoMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${user.init-role}")
    private String[] initRole;

    @Value("${user.init-group}")
    private String initGroup;

    @Value("${user.init-level}")
    private Integer initLevel;

    @Override
    public User login(AuthRequest authRequest) {

        User user = userMapper.selectByAccount(authRequest.getAccount());
        //账户未找到
        Preconditions.checkNotNull(user, ErrorMessage.ACCOUNT_NOT_FOUND);
        //账户被封禁
        Preconditions.checkState(user.getEnable(), ErrorMessage.ACCOUNT_DISABLED);
        //验证密码
        Preconditions.checkArgument(passwordEncoder.matches(authRequest.getPassword(), user.getPassword()), ErrorMessage.ACCOUNT_PASSWORD_ERROR);

        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(AuthRequest authRequest) {

        // 判断账户是否已经存在
        List<String> check = this.checkExist(authRequest);
        Preconditions.checkState(check.size() == 0, check.toString());

        User user = new User();
        user.setCreateTime(new Date());
        user.setEmail(authRequest.getEmail());
        user.setUsername(authRequest.getUsername());
        user.setPhone(authRequest.getPhone());
        user.setGroup(Constant.ROLE_PREFIX + initGroup);
        user.setLevel(initLevel);
        user.setRole(Lists.newArrayList(initRole));
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        userMapper.insertSelective(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getId());
        userInfo.setNickname(user.getUsername());

        userInfoMapper.insertSelective(userInfo);

        return user;
    }

    @Override
    public User one() {

        Integer uid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userMapper.selectByPrimaryKey(uid);
        Preconditions.checkNotNull(user, ErrorMessage.USER_NOT_FOUND);

        user.setPassword(null);

        return user;
    }

    @Override
    public User detail() {

        Integer uid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userMapper.selectByPrimaryKey(uid);
        Preconditions.checkNotNull(user, ErrorMessage.USER_NOT_FOUND);

        UserInfo userInfo = userInfoMapper.selectByUid(uid);
        user.setUserInfo(userInfo);
        user.setPassword(null);

        return user;
    }

    @Override
    public Page<User> pageByParam(Map<String, Object> param) {

        Page<User> userPage = userMapper.listByParam(param);

        List<Integer> uids = Lists.newArrayList();
        userPage.forEach(item -> uids.add(item.getId()));

        Map<Integer, UserInfo> userInfoMap = userInfoMapper.listInUids(uids);
        userPage.forEach(item -> {
            item.setUserInfo(userInfoMap.get(item.getId()));
            item.setPassword(null);
        });

        return userPage;
    }

    private List<String> checkExist(AuthRequest authRequest) {

        List<String> result = Lists.newArrayList();
        if (BooleanUtils.isTrue(userMapper.existUsername(authRequest.getUsername()))) {
            result.add(ErrorMessage.USERNAME_EXIST);
        }
        if (BooleanUtils.isTrue(userMapper.existEmail(authRequest.getEmail()))) {
            result.add(ErrorMessage.EMAIL_EXIST);
        }
        if (BooleanUtils.isTrue(userMapper.existPhone(authRequest.getPhone()))) {
            result.add(ErrorMessage.PHONE_EXIST);
        }

        return result;
    }
}
