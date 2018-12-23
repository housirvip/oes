package vip.housir.base.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author housirvip
 */
@Data
public class UserInfoRequest implements Serializable {

    private String nickname;

    private String avatar;

    private String gender;

    private String school;

    private String major;

    private String province;

    private static final long serialVersionUID = 1L;
}