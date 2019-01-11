package vip.housir.store.mqhandler;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author: housirvip
 */
public interface StoreOutput {

    String ORDER = "store-order-output";

    /**
     * 交易
     *
     * @return MessageChannel
     */
    @Output(ORDER)
    MessageChannel order();
}
