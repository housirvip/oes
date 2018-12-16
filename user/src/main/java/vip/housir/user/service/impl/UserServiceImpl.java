package vip.housir.user.service.impl;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.housir.base.request.AuthRequest;
import vip.housir.user.entity.User;
import vip.housir.user.mapper.UserMapper;
import vip.housir.user.service.UserService;

import java.util.Date;
import java.util.Map;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public User login(AuthRequest authRequest) {

        User user = userMapper.selectByAccount(authRequest.getAccount());
        if (user == null || !user.getEnable()) {
            return null;
        }

        return authMapper.selectByUid(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(AuthRequest authRequest) {

        // 判断账户是否已经存在
        if (userMapper.existAccount(authRequest) != null) {
            return null;
        }

        User user = new User();
        user.setCreateTime(new Date());
        user.setEmail(authRequest.getEmail());
        user.setUsername(authRequest.getUsername());
        user.setNickname(authRequest.getNickname());
        user.setPhone(authRequest.getPhone());

        userMapper.insertSelective(user);

        Auth auth = new Auth();
        auth.setUid(user.getId());
        auth.setCreateTime(user.getCreateTime());
        auth.setPassword(authRequest.getPassword());
        auth.setRole("USER");
        auth.setSecret("secret");

        authMapper.insertSelective(auth);

        return auth;
    }

    @Override
    public Page<User> pageByParam(Map<String, Object> param) {
        return userMapper.selectByParam(param);
    }
}
