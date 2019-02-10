package vip.housir.user.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import vip.housir.base.dto.TradeDto;

/**
 * @author housirvip
 */
@RequiredArgsConstructor
@EnableBinding(value = UserOutput.class)
public class UserSender {

    private final UserOutput userOutput;

    public void sendTradeDto(TradeDto tradeDto) {

        userOutput.order().send(MessageBuilder.withPayload(tradeDto).build());
    }
}
