package vip.housir.user.service;

import com.github.pagehelper.Page;
import vip.housir.base.request.AuthRequest;
import vip.housir.user.entity.User;

import java.util.Map;

/**
 * @author housirvip
 */
public interface UserService {

    /**
     * 验证账户和密码，成功返回 User
     *
     * @param authRequest AuthRequest
     * @return User
     */
    User login(AuthRequest authRequest);

    /**
     * 注册账户
     *
     * @param authRequest AuthRequest
     * @return User
     */
    User register(AuthRequest authRequest);

    /**
     * 根据上下文 uid 获取 User 不包含 UserInfo
     *
     * @return User
     */
    User one();

    /**
     * 根据上下文 uid 获取 User 包含 UserInfo
     *
     * @return User
     */
    User detail();

    /**
     * 根据参数查询，支持分页
     * pageSize,PageNum
     *
     * @param param Map<String,Object>
     * @return List<User>
     */
    Page<User> pageByParam(Map<String, Object> param);
}
