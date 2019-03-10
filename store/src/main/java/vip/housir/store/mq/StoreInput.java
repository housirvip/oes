package vip.housir.store.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: housirvip
 */
public interface StoreInput {

    String ORDER = "order-input";

    String RECHARGE = "recharge-input";

    /**
     * 交易
     *
     * @return SubscribableChannel
     */
    @Input(ORDER)
    SubscribableChannel order();

    /**
     * 充值
     *
     * @return SubscribableChannel
     */
    @Input(RECHARGE)
    SubscribableChannel recharge();
}
