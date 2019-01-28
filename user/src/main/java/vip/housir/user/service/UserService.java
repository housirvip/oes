package vip.housir.user.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.TradeDto;
import vip.housir.base.dto.UserDto;
import vip.housir.user.entity.User;
import vip.housir.user.entity.UserInfo;

import java.util.List;

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
     * 管理员创建账户，返回主键 id
     *
     * @param userDto UserDto
     * @return Integer
     */
    Integer create(UserDto userDto);

    /**
     * 更新认证，刷新 jwt
     *
     * @param uid Integer
     * @return String
     */
    String refresh(Integer uid);

    /**
     * 根据 uid 获取 User 不包含 UserInfo
     *
     * @param uid Integer
     * @return User
     */
    User oneById(Integer uid);

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

    /**
     * 升级用户
     *
     * @param tradeDto TradeDto
     * @return Boolean
     */
    Boolean levelUp(TradeDto tradeDto);

    /**
     * 更新 UserInfo，返回 id
     *
     * @param userInfo UserInfo
     * @return Integer
     */
    Integer update(UserInfo userInfo);

    /**
     * 更新 UserInfo，返回 id
     *
     * @param userInfo UserInfo
     * @return Integer
     */
    Integer updateByUid(UserInfo userInfo);

    /**
     * 更新 User，返回 id
     *
     * @param user User
     * @return Integer
     */
    Integer update(User user);

    /**
     * 分享奖励
     *
     * @param tradeDto TradeDto
     * @return List
     */
    List<String> profit(TradeDto tradeDto);
}
