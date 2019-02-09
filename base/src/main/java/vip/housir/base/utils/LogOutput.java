package vip.housir.base.utils;

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
