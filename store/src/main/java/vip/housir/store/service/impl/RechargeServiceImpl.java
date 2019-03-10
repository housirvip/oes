package vip.housir.store.service.impl;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.housir.base.constant.TradeStatus;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.TradeDto;
import vip.housir.base.mq.DingSender;
import vip.housir.base.utils.JsonUtils;
import vip.housir.store.alipay.BizContent;
import vip.housir.store.entity.Recharge;
import vip.housir.store.mapper.RechargeMapper;
import vip.housir.store.mq.StoreSender;
import vip.housir.store.service.RechargeService;

import java.util.Date;
import java.util.Map;

/**
 * @author housirvip
 */
@Service
@RequiredArgsConstructor
public class RechargeServiceImpl implements RechargeService {

    private final RechargeMapper rechargeMapper;

    private final StoreSender storeSender;
    private final DingSender dingSender;

    @Override
    public Recharge oneById(Integer id, Integer uid) {

        return rechargeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Recharge> pageByParam(PageDto pageDto) {
        return null;
    }

    @Override
    public void update(Recharge recharge) {

        rechargeMapper.updateByPrimaryKeySelective(recharge);
    }

    @Override
    public Integer start(BizContent bizContent, Integer uid) {

        bizContent.setOutTradeNo("testimetech_" + uid + "_" + System.currentTimeMillis());

        Recharge recharge = new Recharge();
        recharge.setCreateTime(new Date());
        recharge.setName(bizContent.getSubject());
        recharge.setTid(bizContent.getOutTradeNo());
        recharge.setStatus(TradeStatus.Pending);
        recharge.setTotalAmount(bizContent.getTotalAmount());
        recharge.setCoin((int) (bizContent.getTotalAmount() * 100));
        recharge.setDiscountAmount(bizContent.getDiscountAmount());
        recharge.setUid(uid);

        rechargeMapper.insertSelective(recharge);

        return recharge.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void finish(Map<String, String> param) {

        Recharge recharge = rechargeMapper.selectByTid(param.get("out_trade_no"));
        if (recharge.getStatus() != TradeStatus.Pending) {
            return;
        }

        recharge.setStatus(TradeStatus.Recharging);
        recharge.setNotifyMsg(JsonUtils.convertToString(param));

        rechargeMapper.updateByPrimaryKeySelective(recharge);

        TradeDto tradeDto = new TradeDto();
        tradeDto.setUid(recharge.getUid());
        tradeDto.setPrice(recharge.getCoin());
        tradeDto.setStatus(recharge.getStatus());
        tradeDto.setReferId(recharge.getId());

        storeSender.sendRecharge(tradeDto);

        dingSender.recharge(recharge.getUid(), recharge.getTotalAmount());
    }
}
