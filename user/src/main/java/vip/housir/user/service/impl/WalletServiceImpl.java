package vip.housir.user.service.impl;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.housir.base.constant.Constant;
import vip.housir.base.request.WalletRequest;
import vip.housir.base.response.ErrorMessage;
import vip.housir.user.entity.User;
import vip.housir.user.entity.Wallet;
import vip.housir.user.mapper.UserMapper;
import vip.housir.user.mapper.WalletMapper;
import vip.housir.user.service.WalletService;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final UserMapper userMapper;
    private final WalletMapper walletMapper;

    @Override
    public Wallet one() {

        Integer uid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return walletMapper.selectByUid(uid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean shopping(WalletRequest walletRequest) {

        Integer uid = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userMapper.selectByPrimaryKey(uid);
        Wallet wallet = walletMapper.selectByUid(uid);

        //TODO 发请求获取商品信息，用户等级检查，余额检查
        Preconditions.checkArgument(user.getLevel() < 99, ErrorMessage.USER_LEVEL_DOWN_DENY);
        Preconditions.checkArgument(wallet.getCoin() >= 100, ErrorMessage.USER_WALLET_LIMIT);

        user.setLevel(3);
        user.setGroup("Vip");
        user.getRole().add(Constant.ROLE_PREFIX + "VIP");
        userMapper.updateByPrimaryKeySelective(user);

        wallet.setCoin(wallet.getCoin() - 100);
        walletMapper.updateByPrimaryKeySelective(wallet);

        return true;
    }
}
