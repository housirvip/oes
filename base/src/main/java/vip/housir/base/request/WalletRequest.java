package vip.housir.base.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author housirvip
 */
@Data
public class WalletRequest implements Serializable {

    @NotNull(groups = {Shopping.class})
    private Integer productId;

    @NotNull(groups = {Shopping.class})
    private Integer coin;

    private static final long serialVersionUID = 1L;
}