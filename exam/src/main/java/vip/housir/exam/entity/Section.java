package vip.housir.exam.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class Section implements Serializable {
    private Integer id;

    private String name;

    private Integer duration;

    private Float totalScore;

    private Float everyScore;

    private Boolean deduct;

    private String type;

    private Integer group;

    private Date createTime;

    private Date updateTime;

    private List<Integer> qids;

    private static final long serialVersionUID = 1L;
}