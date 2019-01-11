package vip.housir.store.mqhandler;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import vip.housir.store.entity.Order;
import vip.housir.store.service.OrderService;

/**
 * @author: housirvip
 * @date: 2019-01-11 19:31
 */
@EnableBinding(StoreInput.class)
@RequiredArgsConstructor
public class StoreHandler {

    private final OrderService orderService;

    @StreamListener(StoreInput.ORDER)
    public void processOrder(Message<Order> msg) {

        orderService.update(msg.getPayload());
    }
}
