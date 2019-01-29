package vip.housir.support.service;

import vip.housir.support.entity.Ticket;

/**
 * @author housirvip
 */
public interface TicketService {
    /**
     * 获取 Ticket 记录，uid 验证是否有权限，render 判断是否装载内容
     *
     * @param id     Integer
     * @param uid    Integer
     * @return Ticket
     */
    Ticket oneById(Integer id, Integer uid);
}
