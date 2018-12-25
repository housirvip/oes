package vip.housir.store.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.housir.base.request.WalletRequest;
import vip.housir.store.service.OrderService;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Override
    public Boolean create(WalletRequest walletRequest) {

        return null;
    }
}
