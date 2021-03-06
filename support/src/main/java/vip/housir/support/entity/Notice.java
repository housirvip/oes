package vip.housir.support.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class Notice implements Serializable {
    private Integer id;

    private String title;

    private String subTitle;

    private Boolean enable;

    private Date createTime;

    private Date updateTime;

    private String content;

    private List<String> pics;

    private static final long serialVersionUID = 1L;
}