package vip.housir.store.service.impl;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vip.housir.base.client.UserClient;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.TradeDto;
import vip.housir.store.entity.Product;
import vip.housir.store.mapper.ProductMapper;
import vip.housir.store.service.TradeService;

import java.util.Optional;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {

    private final ProductMapper productMapper;

    private final UserClient userClient;

    @Override
    public Boolean trade(TradeDto tradeDto) {

        Product product = productMapper.selectByPrimaryKey(tradeDto.getProductId());
        Preconditions.checkNotNull(product, ErrorMessage.PRODUCT_NOT_FOUND);

        BeanUtils.copyProperties(product, tradeDto);

        Optional.of(userClient.trade(tradeDto))
                .ifPresent(rsp -> Preconditions.checkArgument(rsp.getCode() == 0, rsp.getMessage()));

        //TODO 记录订单

        return true;
    }
}
