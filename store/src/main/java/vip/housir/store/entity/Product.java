package vip.housir.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author housirvip
 */
@Data
public class Product implements Serializable {
    private Integer id;

    private Boolean enable;

    private Integer price;

    private String name;

    private String description;

    private Integer minLevel;

    private Integer maxLevel;

    private Integer levelUp;

    private Integer levelTo;

    private String groupTo;

    private String groupLimit;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}