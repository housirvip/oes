package vip.housir.base.mq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author: housirvip
 */
public interface DingOutput {

    String DING = "ding-output";

    /**
     * 日志
     *
     * @return MessageChannel
     */
    @Output(DING)
    MessageChannel push();
}
