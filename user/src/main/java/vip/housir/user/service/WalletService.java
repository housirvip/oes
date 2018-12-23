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
}
