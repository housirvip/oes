package vip.housir.base.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author housirvip
 */
@Data
public class TradeDto implements Serializable {

    private Integer minLevel;

    private Integer maxLevel;

    private Integer levelUp;

    private Integer levelTo;

    @NotNull(groups = Shopping.class)
    private Integer productId;

    @NotNull(groups = Trade.class)
    private Integer price;

    private Integer uid;

    private Integer fromUid;

    private Integer toUid;

    private String groupTo;

    private String groupLimit;

    private String status;

    private static final long serialVersionUID = 1L;
}