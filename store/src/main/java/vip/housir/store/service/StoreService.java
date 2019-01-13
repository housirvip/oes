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
     * 处理交易，生成订单，返回主键 id
     *
     * @param tradeDto TradeDto
     * @return Boolean
     */
    Integer trade(TradeDto tradeDto);

    /**
     * 分页查询 Product
     *
     * @param pageDto PageDto
     * @return Page
     */
    Page<Product> pageProductByParam(PageDto pageDto);

    /**
     * 创建或者修改，返回主键 id
     *
     * @param product Product
     * @return Integer
     */
    Integer productCreateOrUpdate(Product product);
}
