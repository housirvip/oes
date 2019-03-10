package vip.housir.store.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import vip.housir.base.dto.TradeDto;
import vip.housir.store.entity.Order;
import vip.housir.store.entity.Recharge;
import vip.housir.store.service.OrderService;
import vip.housir.store.service.RechargeService;

/**
 * @author: housirvip
 */
@Slf4j
@EnableBinding(StoreInput.class)
@RequiredArgsConstructor
public class StoreHandler {

    private final OrderService orderService;
    private final RechargeService rechargeService;

    @StreamListener(StoreInput.ORDER)
    public void processOrder(Message<TradeDto> msg) {

        TradeDto tradeDto = msg.getPayload();

        Order order = new Order();
        order.setStatus(tradeDto.getStatus());
        order.setId(tradeDto.getReferId());

        try {
            orderService.update(order);
        } catch (Exception e) {
            log.error("订单处理失败:" + e.getMessage() + tradeDto.toString());
        }
    }

    @StreamListener(StoreInput.RECHARGE)
    public void processRecharge(Message<TradeDto> msg) {

        TradeDto tradeDto = msg.getPayload();

        Recharge recharge = new Recharge();
        recharge.setStatus(tradeDto.getStatus());
        recharge.setId(tradeDto.getReferId());

        try {
            rechargeService.update(recharge);
        } catch (Exception e) {
            log.error("订单处理失败:" + e.getMessage() + tradeDto.toString());
        }
    }
}
