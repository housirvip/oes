package vip.housir.store.service;

import vip.housir.base.dto.TradeDto;

/**
 * @author housirvip
 */
public interface TradeService {

    /**
     * 交易
     *
     * @param tradeDto TradeDto
     * @return Boolean
     */
    Boolean trade(TradeDto tradeDto);
}
