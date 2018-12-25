package vip.housir.store.service;

import vip.housir.base.request.WalletRequest;

/**
 * @author housirvip
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param walletRequest WalletRequest
     * @return Boolean
     */
    Boolean create(WalletRequest walletRequest);
}
