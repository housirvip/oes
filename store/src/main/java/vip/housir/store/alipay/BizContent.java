package vip.housir.store.alipay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author housirvip
 */
@Data
public class BizContent {

    @JsonProperty("out_trade_no")
    private String outTradeNo;

    @JsonProperty("total_amount")
    private Float totalAmount;

    @JsonProperty("discountable_amount")
    private Float discountAmount;

    @JsonProperty("subject")
    private String subject;
}
