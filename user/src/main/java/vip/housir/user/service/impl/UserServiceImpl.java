package vip.housir.user.service.impl;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
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

import java.util.ArrayList;
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

    @Override
    public User login(AuthRequest authRequest) {
        // TODO password

        User user = userMapper.selectByAccount(authRequest.getAccount());

        Assert.notNull(user, ErrorMessage.ACCOUNT_NOT_FOUND);
        Assert.isTrue(user.getEnable(), ErrorMessage.ACCOUNT_DIABLED);

        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(AuthRequest authRequest) {

        // 判断账户是否已经存在
        Assert.isNull(userMapper.existUsername(authRequest.getUsername()), ErrorMessage.USERNAME_EXIST);
        Assert.isNull(userMapper.existEmail(authRequest.getEmail()), ErrorMessage.EMAIL_EXIST);
        Assert.isNull(userMapper.existPhone(authRequest.getPhone()), ErrorMessage.PHONE_EXIST);

        List<String> role = new ArrayList<>();
        role.add("USER");

        User user = new User();
        user.setRole(role);
        user.setCreateTime(new Date());
        user.setEmail(authRequest.getEmail());
        user.setUsername(authRequest.getUsername());
        user.setPhone(authRequest.getPhone());
        user.setPassword(authRequest.getPassword());

        userMapper.insertSelective(user);

        UserInfo userInfo = new UserInfo();
        userInfo.setUid(user.getId());
        userInfo.setNickname(user.getUsername());

        userInfoMapper.insertSelective(userInfo);

        return user;
    }

    @Override
    public Page<User> pageByParam(Map<String, Object> param) {
        return userMapper.listByParam(param);
    }
}
