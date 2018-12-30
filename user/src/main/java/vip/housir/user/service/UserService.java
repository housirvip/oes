package vip.housir.user.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.UserDto;
import vip.housir.user.entity.User;
import vip.housir.user.entity.UserInfo;

/**
 * @author housirvip
 */
public interface UserService {
    /**
     * 验证账户和密码，成功返回 jwt
     *
     * @param userDto UserDto
     * @return String
     */
    String login(UserDto userDto);

    /**
     * 注册账户，成功返回 jwt
     *
     * @param userDto UserDto
     * @return String
     */
    String register(UserDto userDto);

    /**
     * 根据 uid 获取 User 不包含 UserInfo
     *
     * @param uid Integer
     * @return User
     */
    User one(Integer uid);

    /**
     * 根据 uid 获取 UserInfo
     *
     * @param uid Integer
     * @return User
     */
    UserInfo info(Integer uid);

    /**
     * 根据 uid 获取 User 包含 UserInfo, Wallet
     *
     * @param uid Integer
     * @return User
     */
    User detail(Integer uid);

    /**
     * 根据参数查询，支持分页
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<User> pageByParam(PageDto pageDto);
}
