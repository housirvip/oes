package vip.housir.base.handler;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author: housirvip
 */
public interface LogOutput {

    String LOG = "log-output";

    /**
     * 日志
     *
     * @return MessageChannel
     */
    @Output(LOG)
    MessageChannel log();
}
