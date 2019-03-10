package vip.housir.user.service.impl;

import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.housir.base.constant.Constant;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.constant.TradeStatus;
import vip.housir.base.dto.TradeDto;
import vip.housir.user.entity.User;
import vip.housir.user.entity.Wallet;
import vip.housir.user.mapper.UserMapper;
import vip.housir.user.mapper.WalletMapper;
import vip.housir.user.service.WalletService;

import java.util.Optional;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final UserMapper userMapper;
    private final WalletMapper walletMapper;

    @Override
    public Wallet oneById(Integer uid) {

        return walletMapper.selectByUid(uid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payForLevel(TradeDto tradeDto) {

        Preconditions.checkArgument(tradeDto.getStatus() == TradeStatus.Pending, ErrorMessage.ORDER_NOT_PENDING);

        User user = userMapper.selectByPrimaryKey(tradeDto.getUid());
        Wallet wallet = walletMapper.selectByUid(tradeDto.getUid());

        //商品信息，用户等级检查，余额检查
        Preconditions.checkArgument(wallet.getCoin() >= tradeDto.getPrice(), ErrorMessage.USER_WALLET_LIMIT);

        Optional.ofNullable(tradeDto.getMaxLevel())
                .ifPresent(max -> Preconditions.checkArgument(user.getLevel() <= max, ErrorMessage.USER_LEVEL_LIMIT));

        Optional.ofNullable(tradeDto.getMinLevel())
                .ifPresent(min -> Preconditions.checkArgument(user.getLevel() >= min, ErrorMessage.USER_LEVEL_LIMIT));

        Optional.ofNullable(tradeDto.getGroupLimit())
                .ifPresent(group -> Preconditions.checkArgument(group.equals(user.getGroup()), ErrorMessage.USER_GROUP_LIMIT));

        user.setGroup(tradeDto.getGroupTo());
        if (!Constant.PRIMARY.equals(user.getGroup()) && !user.getRole().contains(Constant.ROLE_PREFIX + Constant.VIP)) {
            user.getRole().add(Constant.ROLE_PREFIX + Constant.VIP);
        }

        Optional.ofNullable(tradeDto.getLevelUp())
                .ifPresent(up -> user.setLevel(user.getLevel() + up));

        Optional.ofNullable(tradeDto.getLevelTo())
                .ifPresent(to -> {
                    Preconditions.checkArgument(user.getLevel() < to, ErrorMessage.USER_LEVEL_DOWN_DENY);
                    user.setLevel(to);
                });

        userMapper.updateByPrimaryKeySelective(user);

        wallet.setCoin(wallet.getCoin() - tradeDto.getPrice());
        walletMapper.updateByPrimaryKeySelective(wallet);
    }

    @Override
    public Boolean changeCoin(TradeDto tradeDto) {

        Wallet wallet = walletMapper.selectByUid(tradeDto.getUid());
        wallet.setCoin(wallet.getCoin() + tradeDto.getPrice());

        return walletMapper.updateByPrimaryKeySelective(wallet) > 0;
    }
}
