package vip.housir.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author housirvip
 */
@Data
public class ExamDto implements Serializable {
    private Integer id;

    private Integer uid;

    private Integer pid;

    private String name;

    private Float score;

    private Integer duration;

    private Date createTime;

    private Map<Integer, String> userAnswer;

    private Map<Integer, Float> sectionScore;

    private static final long serialVersionUID = 1L;
}