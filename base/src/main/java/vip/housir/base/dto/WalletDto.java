package vip.housir.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author housirvip
 */
@Data
public class WalletDto implements Serializable {

    private Integer id;

    private Integer uid;

    private Integer coin;

    private Integer freeze;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}