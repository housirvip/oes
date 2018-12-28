package vip.housir.store.service;

import com.github.pagehelper.Page;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.TradeDto;
import vip.housir.store.entity.Product;

/**
 * @author housirvip
 */
public interface StoreService {

    /**
     * 交易
     *
     * @param tradeDto TradeDto
     * @return Boolean
     */
    Boolean trade(TradeDto tradeDto);

    Page<Product> pageProductByParam(PageDto pageDto);
}
