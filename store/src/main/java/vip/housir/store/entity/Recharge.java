package vip.housir.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author housirvip
 */
@Data
public class Recharge implements Serializable {
    private Integer id;

    private Integer uid;

    private Integer coin;

    private Float totalAmount;

    private String discountAmount;

    private String name;

    private String status;

    private String tid;

    private String payType;

    private Date createTime;

    private Date updateTime;

    private String notifyMsg;
}