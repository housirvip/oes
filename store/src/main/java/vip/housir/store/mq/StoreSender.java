package vip.housir.store.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import vip.housir.base.constant.TradeType;
import vip.housir.base.dto.TradeDto;

/**
 * @author housirvip
 */
@RequiredArgsConstructor
@EnableBinding(StoreOutput.class)
public class StoreSender {

    private final StoreOutput storeOutput;

    public void sendOrder(TradeDto tradeDto) {

        tradeDto.setType(TradeType.Order);

        storeOutput.order().send(MessageBuilder.withPayload(tradeDto).build());
    }

    public void sendRecharge(TradeDto tradeDto) {

        tradeDto.setType(TradeType.Recharge);

        storeOutput.recharge().send(MessageBuilder.withPayload(tradeDto).build());
    }
}
