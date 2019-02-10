package vip.housir.support.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author: housirvip
 */
public interface LogInput {

    String LOG = "log-input";

    /**
     * 日志
     *
     * @return SubscribableChannel
     */
    @Input(LOG)
    SubscribableChannel log();
}
