package vip.housir.base.dto;

import lombok.Data;
import vip.housir.base.constant.TradeStatus;
import vip.housir.base.constant.TradeType;
import vip.housir.base.constant.UserGroup;

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

    private Integer referId;

    @NotNull(groups = Award.class)
    private Integer price;

    @NotNull(groups = Award.class)
    private Integer uid;

    @NotNull(groups = Profit.class)
    private List<String> phones;

    private Integer fromUid;

    private Integer toUid;

    private UserGroup groupTo;

    private UserGroup groupLimit;

    private TradeStatus status;

    private TradeType type;

    private Boolean levelDown;

    private Float totalAmount;

    private static final long serialVersionUID = 1L;
}