package vip.housir.support.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.housir.support.entity.Ticket;

/**
 * @author housirvip
 */
@Mapper
public interface TicketMapper {
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
     * @param record Ticket
     * @return int
     */
    int insert(Ticket record);

    /**
     * 插入一条记录返回值为 ID，对象中属性值为 null 则不赋值，取数据库默认值
     *
     * @param record Ticket
     * @return int
     */
    int insertSelective(Ticket record);

    /**
     * 根据主键查询记录，返回一条记录或者 null
     *
     * @param id Integer
     * @return Ticket
     */
    Ticket selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则不赋值
     *
     * @param record Ticket
     * @return int
     */
    int updateByPrimaryKeySelective(Ticket record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则数据库赋值为 null
     *
     * @param record Ticket
     * @return int
     */
    int updateByPrimaryKey(Ticket record);
}