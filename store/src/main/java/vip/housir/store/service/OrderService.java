package vip.housir.store.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.TradeDto;
import vip.housir.store.entity.Order;

/**
 * @author housirvip
 */
public interface OrderService {

    /**
     * 获取 Order 记录
     *
     * @param id  Integer
     * @param uid Integer
     * @return Order
     */
    Order oneById(Integer id, Integer uid);

    /**
     * 根据参数查询，支持分页
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<Order> pageByParam(PageDto pageDto);

    /**
     * 更新订单
     *
     * @param order Order
     */
    void update(Order order);

    /**
     * 开始交易，生成订单，返回主键 id
     *
     * @param tradeDto TradeDto
     * @return Integer
     */
    Integer start(TradeDto tradeDto);
}
