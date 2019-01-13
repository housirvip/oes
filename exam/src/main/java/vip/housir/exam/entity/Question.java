package vip.housir.exam.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author housirvip
 */
@Data
public class Question implements Serializable {
    private Integer id;

    private Integer sid;

    private String type;

    private String pic;

    private String answer;

    private String expPic;

    private Date createTime;

    private Date updateTime;

    private String stem;

    private String answerFull;

    private Map<String, String> selections;

    private String explain;

    private static final long serialVersionUID = 1L;
}