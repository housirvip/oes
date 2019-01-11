package vip.housir.user.mqhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import vip.housir.base.dto.TradeDto;
import vip.housir.user.service.WalletService;

/**
 * @author: housirvip
 * @date: 2019-01-11 19:31
 */
@Slf4j
@EnableBinding(UserInput.class)
@RequiredArgsConstructor
public class UserHandler {

    private final WalletService walletService;

    @StreamListener(UserInput.ORDER)
    public void processOrder(Message<TradeDto> msg) {

        log.info(msg.getPayload().toString());
        walletService.payForLevel(msg.getPayload());
    }
}
