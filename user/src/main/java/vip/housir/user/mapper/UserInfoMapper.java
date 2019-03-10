package vip.housir.user.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import vip.housir.user.entity.UserInfo;

import java.util.List;
import java.util.Map;

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
     * @param record UserInfo
     * @return int
     */
    int insert(UserInfo record);

    /**
     * 插入一条记录返回值为 ID，对象中属性值为 null 则不赋值，取数据库默认值
     *
     * @param record UserInfo
     * @return int
     */
    int insertSelective(UserInfo record);

    /**
     * 根据主键查询记录，返回一条记录或者 null
     *
     * @param id Integer
     * @return UserInfo
     */
    UserInfo selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录，返回受影响的记录数，对象中属性值为 null，则不赋值
     *
     * @param record UserInfo
     * @return int
     */
    int updateByPrimaryKeySelective(UserInfo record);

    /**
     * 根据主键更新记录，返回受影响的记录数，对象中属性值为 null，则数据库赋值为 null
     *
     * @param record UserInfo
     * @return int
     */
    int updateByPrimaryKey(UserInfo record);

    /**
     * 根据主键更新记录，返回受影响的记录数，对象中属性值为 null，则不赋值
     *
     * @param record UserInfo
     * @return int
     */
    int updateByUidSelective(UserInfo record);

    /**
     * 根据 uid 查询 UserInfo
     *
     * @param uid Integer
     * @return UserInfo
     */
    UserInfo selectByUid(Integer uid);

    /**
     * 获取 id 在 ids 列表中的 UserInfo 列表
     * Map 的 key 为 uid，value 为 UserInfo
     *
     * @param uids List<Integer>
     * @return Map
     */
    @MapKey("uid")
    Map<Integer, UserInfo> listInUids(List<Integer> uids);
}