package vip.housir.user.service;

import vip.housir.user.entity.Wallet;

/**
 * @author housirvip
 */
public interface WalletService {
    /**
     * 根据上下文 uid 获取 Wallet
     *
     * @return Wallet
     */
    Wallet one();

    /**
     * 根据商品 id 进行购买
     *
     * @param id Integer
     * @return Boolean
     */
    Boolean buy(Integer id);
}
