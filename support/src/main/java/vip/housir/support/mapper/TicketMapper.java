package vip.housir.support.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.housir.support.entity.Ticket;

@Mapper
public interface TicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    Ticket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);
}