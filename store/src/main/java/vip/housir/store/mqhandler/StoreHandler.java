package vip.housir.store.mqhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import vip.housir.base.dto.TradeDto;
import vip.housir.store.entity.Order;
import vip.housir.store.service.OrderService;

/**
 * @author: housirvip
 * @date: 2019-01-11 19:31
 */
@Slf4j
@EnableBinding(StoreInput.class)
@RequiredArgsConstructor
public class StoreHandler {

    private final OrderService orderService;

    @StreamListener(StoreInput.ORDER)
    public void processOrder(Message<TradeDto> msg) {

        TradeDto tradeDto = msg.getPayload();
        log.info(tradeDto.toString());
        Order order = new Order();
        order.setStatus(tradeDto.getStatus());
        order.setId(tradeDto.getOrderId());
        orderService.update(order);
    }
}
