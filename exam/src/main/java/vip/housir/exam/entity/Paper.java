package vip.housir.exam.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class Paper implements Serializable {
    private Integer id;

    private String name;

    private String type;

    private String group;

    private Boolean enable;

    private Integer minLevel;

    private Float totalScore;

    private Float passScore;

    private Float avgScore;

    private Integer duration;

    private String description;

    private Date createTime;

    private Date updateTime;

    private List<Integer> sids;

    private static final long serialVersionUID = 1L;
}