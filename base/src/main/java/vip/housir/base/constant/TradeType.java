package vip.housir.base.constant;

import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
public enum TradeType {

    /**
     * Award 奖励
     */
    Award("award"),
    /**
     * Order 订购
     */
    Order("order"),
    /**
     * Profit 返利
     */
    Profit("profit"),
    /**
     * Purchase 购买
     */
    Purchase("purchase"),
    /**
     * Recharge 充值
     */
    Recharge("recharge");

    private String value;

    TradeType(String value) {
        this.value = value;
    }
}
