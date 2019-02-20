package vip.housir.support.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: housirvip
 */
public interface DingInput {

    String DING = "ding-input";

    /**
     * 日志
     *
     * @return SubscribableChannel
     */
    @Input(DING)
    SubscribableChannel pull();
}
