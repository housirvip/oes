package vip.housir.user.mqhandler;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author: housirvip
 */
public interface UserOutput {

    String ORDER = "order-output";

    /**
     * 交易
     *
     * @return MessageChannel
     */
    @Output(ORDER)
    MessageChannel order();
}
