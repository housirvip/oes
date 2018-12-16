package vip.housir.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author housirvip
 */
@Data
public class UserInfo implements Serializable {
    private Integer id;

    private Integer uid;

    private String nickname;

    private String avatar;

    private String gender;

    private String school;

    private String major;

    private String province;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}