package vip.housir.base.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum TicketModule {

    /**
     * 账户
     */
    Account("account"),
    /**
     * 钱包
     */
    Wallet("wallet"),
    /**
     * 试卷
     */
    Paper("paper"),
    /**
     * 题目
     */
    Question("question");

    private String value;
}
