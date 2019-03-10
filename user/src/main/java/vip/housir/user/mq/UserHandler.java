package vip.housir.user.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import vip.housir.base.constant.TradeStatus;
import vip.housir.base.dto.TradeDto;
import vip.housir.base.mq.DingSender;
import vip.housir.user.service.WalletService;

/**
 * @author: housirvip
 */
@Slf4j
@EnableBinding(UserInput.class)
@RequiredArgsConstructor
public class UserHandler {

    private final WalletService walletService;

    private final UserSender userSender;
    private final DingSender dingSender;

    @StreamListener(UserInput.ORDER)
    public void processOrder(Message<TradeDto> msg) {

        TradeDto tradeDto = msg.getPayload();

        try {
            walletService.payForLevel(tradeDto);
            tradeDto.setStatus(TradeStatus.Success);
        } catch (Exception ex) {
            tradeDto.setStatus(TradeStatus.Failed);
            log.error("订单处理失败:" + ex.getMessage() + tradeDto.toString());
        }

        userSender.sendOrder(tradeDto);
    }

    @StreamListener(UserInput.RECHARGE)
    public void processRecharge(Message<TradeDto> msg) {

        TradeDto tradeDto = msg.getPayload();

        if (tradeDto.getStatus() != TradeStatus.Recharging) {
            return;
        }

        try {
            walletService.changeCoin(tradeDto);
            tradeDto.setStatus(TradeStatus.Success);
        } catch (Exception ex) {
            tradeDto.setStatus(TradeStatus.Failed);
            dingSender.exception(ex);
            log.error("充值处理失败:" + ex.getMessage() + tradeDto.toString());
        }

        userSender.sendRecharge(tradeDto);
    }
}
