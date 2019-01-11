package vip.housir.user.mqhandler;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: housirvip
 */
public interface UserInput {

    String ORDER = "user-order-input";

    /**
     * 交易
     *
     * @return SubscribableChannel
     */
    @Input(ORDER)
    SubscribableChannel order();
}
