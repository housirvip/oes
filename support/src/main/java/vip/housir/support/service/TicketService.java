package vip.housir.support.service;

import vip.housir.support.entity.Ticket;

/**
 * @author housirvip
 */
public interface TicketService {
    /**
     * 获取 Ticket 记录，uid 验证是否有权限
     *
     * @param id  Integer
     * @param uid Integer
     * @return Ticket
     */
    Ticket oneById(Integer id, Integer uid);
}
