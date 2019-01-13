package vip.housir.support.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.housir.support.entity.TicketContent;

@Mapper
public interface TicketContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TicketContent record);

    int insertSelective(TicketContent record);

    TicketContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TicketContent record);

    int updateByPrimaryKeyWithBLOBs(TicketContent record);

    int updateByPrimaryKey(TicketContent record);
}