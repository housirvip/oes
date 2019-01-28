package vip.housir.user.mqhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import vip.housir.base.constant.Constant;
import vip.housir.base.dto.TradeDto;
import vip.housir.user.service.WalletService;

/**
 * @author: housirvip
 */
@Slf4j
@EnableBinding(UserInput.class)
@RequiredArgsConstructor
public class UserHandler {

    private final WalletService walletService;

    @StreamListener(UserInput.ORDER)
    public void processOrder(Message<TradeDto> msg) {

        TradeDto tradeDto = msg.getPayload();

        try {
            walletService.payForLevel(tradeDto);
        } catch (Exception e) {
            tradeDto.setStatus(Constant.ERROR);
            walletService.sendTradeDto(tradeDto);
            log.error("订单处理失败:" + e.getMessage() + tradeDto.toString());
        }
    }
}
