package vip.housir.base.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class TradeDto implements Serializable {

    private Integer minLevel;

    private Integer maxLevel;

    private Integer levelUp;

    @NotNull(groups = Profit.class)
    private Integer levelTo;

    @NotNull(groups = Shopping.class)
    private Integer productId;

    private Integer orderId;

    @NotNull(groups = Award.class)
    private Integer price;

    @NotNull(groups = Award.class)
    private Integer uid;

    @NotNull(groups = Profit.class)
    private List<String> phones;

    private Integer fromUid;

    private Integer toUid;

    private String groupTo;

    private String groupLimit;

    private String status;

    private Boolean levelDown;

    private static final long serialVersionUID = 1L;
}