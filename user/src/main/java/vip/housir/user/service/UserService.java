package vip.housir.user.service;

import com.github.pagehelper.Page;
import vip.housir.base.request.PageRequest;
import vip.housir.base.request.UserRequest;
import vip.housir.user.entity.User;

/**
 * @author housirvip
 */
public interface UserService {
    /**
     * 验证账户和密码，成功返回 jwt
     *
     * @param userRequest UserRequest
     * @return String
     */
    String login(UserRequest userRequest);

    /**
     * 注册账户，成功返回 jwt
     *
     * @param userRequest UserRequest
     * @return String
     */
    String register(UserRequest userRequest);

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
     *
     * @param pageRequest PageRequest
     * @return Page
     */
    Page<User> pageByParam(PageRequest pageRequest);
}
