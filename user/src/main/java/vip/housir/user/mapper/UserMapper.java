package vip.housir.user.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import vip.housir.base.request.AuthRequest;
import vip.housir.user.entity.User;

import java.util.Map;

/**
 * @author housirvip
 */
@Mapper
public interface UserMapper {
    /**
     * 通过主键删除
     *
     * @param id Integer
     * @return int 删除的行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一条记录返回值为ID，对象中属性值为null，则数据库赋值为null
     *
     * @param record User
     * @return int
     */
    int insert(User record);

    /**
     * 插入一条记录返回值为ID，对象中属性值为null则不赋值，取数据库默认值
     *
     * @param record User
     * @return int
     */
    int insertSelective(User record);

    /**
     * 根据主键查询记录，返回一条记录或者null
     *
     * @param id Integer
     * @return User
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为null，则不赋值
     *
     * @param record User
     * @return int
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为null，则数据库赋值为null
     *
     * @param record User
     * @return int
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为null，则数据库赋值为null
     *
     * @param record User
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(User record);

    /**
     * 根据 account 查找账户
     *
     * @param account String
     * @return User
     */
    User selectByAccount(String account);

    /**
     * 判断 username 是否已存在
     *
     * @param username String
     * @return 1 or null
     */
    Integer existUsername(String username);

    /**
     * 判断 email 是否已存在
     *
     * @param email String
     * @return 1 or null
     */
    Integer existEmail(String email);

    /**
     * 判断 phone 是否已存在
     *
     * @param phone String
     * @return 1 or null
     */
    Integer existPhone(String phone);

    /**
     * 根据参数查询，支持分页
     *
     * @param param Map<String,Object>
     * @return Page<User>
     */
    Page<User> listByParam(Map<String, Object> param);
}