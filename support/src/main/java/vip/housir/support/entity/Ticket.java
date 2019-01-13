package vip.housir.support.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author housirvip
 */
@Data
public class Ticket implements Serializable {
    private Integer id;

    private Integer uid;

    private String status;

    private Integer rate;

    private String module;

    private String title;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}