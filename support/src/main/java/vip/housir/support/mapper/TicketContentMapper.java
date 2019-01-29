package vip.housir.support.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.housir.support.entity.TicketContent;

import java.util.List;

/**
 * @author housirvip
 */
@Mapper
public interface TicketContentMapper {
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
     * @param record Question
     * @return int
     */
    int insert(TicketContent record);

    /**
     * 插入一条记录返回值为 ID，对象中属性值为 null 则不赋值，取数据库默认值
     *
     * @param record TicketContent
     * @return int
     */
    int insertSelective(TicketContent record);

    /**
     * 根据主键查询记录，返回一条记录或者 null
     *
     * @param id Integer
     * @return TicketContent
     */
    TicketContent selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则不赋值
     *
     * @param record TicketContent
     * @return int
     */
    int updateByPrimaryKeySelective(TicketContent record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则数据库赋值为 null
     *
     * @param record TicketContent
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(TicketContent record);

    /**
     * 根据主键更新记录，返回受影响的记录数
     * 对象中属性值为 null，则数据库赋值为 null
     *
     * @param record TicketContent
     * @return int
     */
    int updateByPrimaryKey(TicketContent record);

    /**
     * 根据 tid 查询所有工单内容
     * @param tid Integer
     * @return List
     */
    List<TicketContent> selectByTid(Integer tid);
}