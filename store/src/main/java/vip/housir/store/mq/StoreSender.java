package vip.housir.store.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import vip.housir.base.dto.TradeDto;

/**
 * @author housirvip
 */
@RequiredArgsConstructor
@EnableBinding(StoreOutput.class)
public class StoreSender {

    private final StoreOutput storeOutput;

    public void sendTradeDto(TradeDto tradeDto) {

        storeOutput.order().send(MessageBuilder.withPayload(tradeDto).build());
    }
}
