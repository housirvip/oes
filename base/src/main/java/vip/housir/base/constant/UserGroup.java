package vip.housir.base.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum UserGroup {

    /**
     * Award 奖励
     */
    Primary("primary"),
    /**
     * Order 订购
     */
    User("user"),
    /**
     * Profit 返利
     */
    Vip("vip"),
    /**
     * Purchase 购买
     */
    Admin("admin"),
    /**
     * Recharge 充值
     */
    Root("root");

    private String value;
}
