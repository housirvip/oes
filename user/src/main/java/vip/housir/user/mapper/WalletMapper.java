package vip.housir.user.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import vip.housir.user.entity.Wallet;

import java.util.List;
import java.util.Map;

/**
 * @author housirvip
 */
@Mapper
public interface WalletMapper {
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
    int insert(Wallet record);

    /**
     * 插入一条记录返回值为 ID，对象中属性值为 null 则不赋值，取数据库默认值
     *
     * @param record User
     * @return int
     */
    int insertSelective(Wallet record);

    /**
     * 根据主键查询记录，返回一条记录或者 null
     *
     * @param id Integer
     * @return Wallet
     */
    Wallet selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则不赋值
     *
     * @param record User
     * @return int
     */
    int updateByPrimaryKeySelective(Wallet record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则数据库赋值为 null
     *
     * @param record User
     * @return int
     */
    int updateByPrimaryKey(Wallet record);

    /**
     * 根据 uid 查询 Wallet
     *
     * @param uid Integer
     * @return Wallet
     */
    Wallet selectByUid(Integer uid);

    /**
     * 获取 id 在 ids 列表中的 Wallet 列表
     * Map 的 key 为 uid，value 为 Wallet
     *
     * @param uids List<Integer>
     * @return Map
     */
    @MapKey("uid")
    Map<Integer, Wallet> listInUids(List<Integer> uids);
}