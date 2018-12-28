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

    private String name;

    private String status;

    private String qrId;

    private String qrPayId;

    private String tid;

    private String payType;

    private Integer payTypeCode;

    private Date createTime;

    private Date updateTime;

    private String yzMsg;

    private static final long serialVersionUID = 1L;
}