package vip.housir.store.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import vip.housir.base.constant.Constant;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.TradeDto;
import vip.housir.store.entity.Order;
import vip.housir.store.entity.Product;
import vip.housir.store.mapper.OrderMapper;
import vip.housir.store.mapper.ProductMapper;
import vip.housir.store.mqhandler.StoreOutput;
import vip.housir.store.service.StoreService;

import java.util.Date;

/**
 * @author housirvip
 */
@Service
@EnableBinding(StoreOutput.class)
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;

    private final StoreOutput storeOutput;

    @Override
    public Boolean trade(TradeDto tradeDto) {

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
        BeanUtils.copyProperties(product, tradeDto);
        tradeDto.setProductId(product.getId());
        tradeDto.setOrderId(order.getId());

        return storeOutput.order().send(MessageBuilder.withPayload(tradeDto).build());
    }

    @Override
    public Page<Product> pageProductByParam(PageDto pageDto) {

        return productMapper.listByParam(pageDto.putParam().getParamAsMap());
    }
}
