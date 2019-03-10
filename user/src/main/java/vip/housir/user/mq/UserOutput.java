package vip.housir.user.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author: housirvip
 */
public interface UserOutput {

    String ORDER = "order-output";

    String RECHARGE = "recharge-output";

    /**
     * 交易
     *
     * @return MessageChannel
     */
    @Output(ORDER)
    MessageChannel order();

    /**
     * 充值
     *
     * @return MessageChannel
     */
    @Output(RECHARGE)
    MessageChannel recharge();
}
