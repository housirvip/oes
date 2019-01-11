package vip.housir.store.mqhandler;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: housirvip
 */
public interface StoreInput {

    String ORDER = "order-input";

    /**
     * 交易
     *
     * @return SubscribableChannel
     */
    @Input(ORDER)
    SubscribableChannel order();
}
