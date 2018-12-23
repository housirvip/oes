package vip.housir.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vip.housir.user.entity.Wallet;
import vip.housir.user.mapper.WalletMapper;
import vip.housir.user.service.WalletService;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletMapper walletMapper;

    @Override
    public Wallet one() {

        Integer uid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return walletMapper.selectByUid(uid);
    }
}
