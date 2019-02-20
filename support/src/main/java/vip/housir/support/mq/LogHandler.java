package vip.housir.support.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import vip.housir.base.dto.DingDto;
import vip.housir.support.client.DingClient;

/**
 * @author: housirvip
 */
@Slf4j
@EnableBinding(DingInput.class)
@RequiredArgsConstructor
public class LogHandler {

    private final DingClient dingClient;

    @StreamListener(DingInput.DING)
    public void processLog(Message<DingDto> msg) {

        dingClient.send(msg.getPayload());
    }
}
