package vip.housir.store.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vip.housir.base.client.UserClient;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.TradeDto;
import vip.housir.store.entity.Order;
import vip.housir.store.entity.Product;
import vip.housir.store.mapper.OrderMapper;
import vip.housir.store.mapper.ProductMapper;
import vip.housir.store.service.StoreService;

import java.util.Date;
import java.util.Optional;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;

    private final UserClient userClient;

    @Override
    public Boolean trade(TradeDto tradeDto) {

        Product product = productMapper.selectByPrimaryKey(tradeDto.getProductId());
        Preconditions.checkNotNull(product, ErrorMessage.PRODUCT_NOT_FOUND);

        //拷贝交易信息
        BeanUtils.copyProperties(product, tradeDto);

        Optional.of(userClient.trade(tradeDto))
                .ifPresent(rsp -> Preconditions.checkArgument(rsp.getCode() == 0, rsp.getMessage()));

        //记录订单
        Order order = new Order();
        order.setUid(tradeDto.getUid());
        order.setCreateTime(new Date());
        order.setPrice(product.getPrice());
        order.setProductId(product.getId());
        order.setProductName(product.getName());
        orderMapper.insertSelective(order);

        return true;
    }

    @Override
    public Page<Product> pageProductByParam(PageDto pageDto) {

        return productMapper.listByParam(pageDto.putParam().getParamAsMap());
    }
}
