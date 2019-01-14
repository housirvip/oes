package vip.housir.store.service.impl;

import com.github.pagehelper.Page;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.PageDto;
import vip.housir.store.entity.Order;
import vip.housir.store.mapper.OrderMapper;
import vip.housir.store.service.OrderService;

import java.util.Optional;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Override
    public Order oneById(Integer id, Integer uid) {

        Order order = orderMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(order, ErrorMessage.ORDER_NOT_FOUND);

        Optional.ofNullable(uid)
                .ifPresent(u -> Preconditions.checkArgument(u.equals(order.getUid()), ErrorMessage.ORDER_PERMISSION_DENY));

        return order;
    }

    @Override
    public Page<Order> pageByParam(PageDto pageDto) {

        return orderMapper.listByParam(pageDto.putParam().getParamAsMap());
    }

    @Override
    public void update(Order order) {

        orderMapper.updateByPrimaryKeySelective(order);
    }
}
