package vip.housir.store.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.housir.base.constant.Constant;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.TradeDto;
import vip.housir.store.entity.Order;
import vip.housir.store.entity.Product;
import vip.housir.store.mapper.OrderMapper;
import vip.housir.store.mapper.ProductMapper;
import vip.housir.store.mq.StoreSender;
import vip.housir.store.service.StoreService;

import java.util.Date;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;

    private final StoreSender storeSender;

    @Override
    public Integer trade(TradeDto tradeDto) {

        Product product = productMapper.selectByPrimaryKey(tradeDto.getProductId());
        Preconditions.checkNotNull(product, ErrorMessage.PRODUCT_NOT_FOUND);

        //记录订单
        Order order = new Order();
        order.setUid(tradeDto.getUid());
        order.setCreateTime(new Date());
        order.setPrice(product.getPrice());
        order.setProductId(product.getId());
        order.setProductName(product.getName());
        order.setStatus(Constant.PENDING);

        orderMapper.insertSelective(order);

        //拷贝交易信息
        tradeDto.setOrderId(order.getId());
        tradeDto.setStatus(order.getStatus());
        tradeDto.setMinLevel(product.getMinLevel());
        tradeDto.setMaxLevel(product.getMaxLevel());
        tradeDto.setLevelUp(product.getLevelUp());
        tradeDto.setLevelTo(product.getLevelTo());
        tradeDto.setProductId(product.getId());
        tradeDto.setPrice(product.getPrice());
        tradeDto.setGroupTo(product.getGroupTo());
        tradeDto.setGroupLimit(product.getGroupLimit());

        storeSender.sendTradeDto(tradeDto);

        return order.getId();
    }

    @Override
    public Page<Product> pageProductByParam(PageDto pageDto) {

        return productMapper.listByParam(pageDto.putParam().getParamAsMap());
    }

    @Override
    public Integer productCreateOrUpdate(Product product) {

        if (product.getId() == null) {
            product.setCreateTime(new Date());
            productMapper.insertSelective(product);
        } else {
            productMapper.updateByPrimaryKeySelective(product);
        }

        return product.getId();
    }
}
