package vip.housir.base.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum TradeStatus {

    /**
     * Failed 失败
     */
    Failed("failed"),
    /**
     * Failure 失败
     */
    Failure("failure"),
    /**
     * Pending 待支付
     */
    Pending("pending"),
    /**
     * Recharging 充值中
     */
    Recharging("recharging"),
    /**
     * Success 成功
     */
    Success("success");

    private String value;
}
