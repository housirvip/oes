package vip.housir.user.service.impl;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import vip.housir.base.request.AuthRequest;
import vip.housir.base.response.ErrorMessage;
import vip.housir.user.entity.User;
import vip.housir.user.entity.UserInfo;
import vip.housir.user.mapper.UserInfoMapper;
import vip.housir.user.mapper.UserMapper;
import vip.housir.user.service.UserService;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

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
        Assert.notNull(user, ErrorMessage.ACCOUNT_NOT_FOUND);
        //账户被封禁
        Assert.isTrue(user.getEnable(), ErrorMessage.ACCOUNT_DISABLED);
        //验证密码
        Assert.isTrue(passwordEncoder.matches(authRequest.getPassword(), user.getPassword()), ErrorMessage.ACCOUNT_PASSWORD_ERROR);

        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(AuthRequest authRequest) {

        // 判断账户是否已经存在
        Assert.isNull(userMapper.existUsername(authRequest.getUsername()), ErrorMessage.USERNAME_EXIST);
        Assert.isNull(userMapper.existEmail(authRequest.getEmail()), ErrorMessage.EMAIL_EXIST);
        Assert.isNull(userMapper.existPhone(authRequest.getPhone()), ErrorMessage.PHONE_EXIST);

        User user = new User();
        user.setCreateTime(new Date());
        user.setEmail(authRequest.getEmail());
        user.setUsername(authRequest.getUsername());
        user.setPhone(authRequest.getPhone());
        user.setGroup(initGroup);
        user.setLevel(initLevel);
        user.setRole(Arrays.asList(initRole));
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        userMapper.insertSelective(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getId());
        userInfo.setNickname(user.getUsername());

        userInfoMapper.insertSelective(userInfo);

        return user;
    }

    @Override
    public User detail(Integer uid) {

        User user = userMapper.selectByPrimaryKey(uid);
        Assert.notNull(user, ErrorMessage.USER_NOT_FOUND);

        UserInfo userInfo = userInfoMapper.selectByUid(uid);
        user.setUserInfo(userInfo);

        return user;
    }

    @Override
    public Page<User> pageByParam(Map<String, Object> param) {

        return userMapper.listByParam(param);
    }
}
