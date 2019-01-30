package vip.housir.support.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.TicketDto;
import vip.housir.support.entity.Ticket;

/**
 * @author housirvip
 */
public interface TicketService {
    /**
     * 获取 Ticket 记录，uid 验证是否有权限
     *
     * @param id     Integer
     * @param uid    Integer
     * @return Ticket
     */
    Ticket oneById(Integer id, Integer uid);

    /**
     * 根据参数查询，支持分页
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<Ticket> pageByParam(PageDto pageDto);

    /**
     * 创建 Ticket
     * @param ticketDto TicketDto
     * @return Integer
     */
    Integer create(TicketDto ticketDto);

    /**
     * 创建 Ticket
     * @param ticketDto TicketDto
     * @return Integer
     */
    Integer reply(TicketDto ticketDto);

    /**
     * 关闭 Ticket
     * @param ticketDto TicketDto
     * @return Integer
     */
    Integer close(TicketDto ticketDto);
}
