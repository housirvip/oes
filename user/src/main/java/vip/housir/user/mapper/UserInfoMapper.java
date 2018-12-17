package vip.housir.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.housir.user.entity.UserInfo;

/**
 * @author housirvip
 */
@Mapper
public interface UserInfoMapper {
    /**
     * 通过主键删除
     *
     * @param id Integer
     * @return int 删除的行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一条记录返回值为 ID，对象中属性值为 null，则数据库赋值为 null
     *
     * @param record User
     * @return int
     */
    int insert(UserInfo record);

    /**
     * 插入一条记录返回值为 ID，对象中属性值为 null 则不赋值，取数据库默认值
     *
     * @param record User
     * @return int
     */
    int insertSelective(UserInfo record);

    /**
     * 根据主键查询记录，返回一条记录或者 null
     *
     * @param id Integer
     * @return User
     */
    UserInfo selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录，返回受影响的记录数，对象中属性值为 null，则不赋值
     *
     * @param record User
     * @return int
     */
    int updateByPrimaryKeySelective(UserInfo record);

    /**
     * 根据主键更新记录，返回受影响的记录数，对象中属性值为 null，则数据库赋值为 null
     *
     * @param record User
     * @return int
     */
    int updateByPrimaryKey(UserInfo record);

    /**
     * 根据 uid 查询 UserInfo
     *
     * @param uid Integer
     * @return User
     */
    UserInfo selectByUid(Integer uid);
}