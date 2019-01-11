package vip.housir.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author housirvip
 */
@Data
public class Order implements Serializable {
    private Integer id;

    private Integer uid;

    private String productName;

    private Integer productId;

    private Integer price;

    private String status;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}