package vip.housir.user.service;

import vip.housir.base.dto.TradeDto;
import vip.housir.user.entity.Wallet;

/**
 * @author housirvip
 */
public interface WalletService {
    /**
     * 根据上下文 uid 获取 Wallet
     *
     * @param uid Integer
     * @return Wallet
     */
    Wallet one(Integer uid);

    /**
     * 根据商品，生效交易
     *
     * @param tradeDto TradeDto
     */
    void payForLevel(TradeDto tradeDto);

    /**
     * 奖励
     *
     * @param tradeDto TradeDto
     * @return Boolean
     */
    Boolean award(TradeDto tradeDto);
}
