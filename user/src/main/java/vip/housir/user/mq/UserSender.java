package vip.housir.user.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import vip.housir.base.constant.TradeType;
import vip.housir.base.dto.TradeDto;

/**
 * @author housirvip
 */
@RequiredArgsConstructor
@EnableBinding(value = UserOutput.class)
public class UserSender {

    private final UserOutput userOutput;

    public void sendOrder(TradeDto tradeDto) {

        tradeDto.setType(TradeType.Order);

        userOutput.order().send(MessageBuilder.withPayload(tradeDto).build());
    }

    public void sendRecharge(TradeDto tradeDto) {

        tradeDto.setType(TradeType.Recharge);

        userOutput.recharge().send(MessageBuilder.withPayload(tradeDto).build());
    }
}
