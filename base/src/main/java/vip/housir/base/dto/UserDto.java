package vip.housir.base.dto;


import lombok.Data;
import vip.housir.base.constant.UserGroup;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class UserDto implements Serializable {

    @NotBlank(groups = {Login.class})
    private String account;

    @NotBlank(groups = {Register.class})
    private String phone;

    @NotBlank(groups = {Register.class})
    private String username;

    @NotBlank(groups = {Login.class, Register.class})
    private String password;

    @NotBlank(groups = {Register.class})
    private String email;

    private Boolean remember;

    private String code;

    private String captcha;

    private Integer id;

    private Date createTime;

    private Date updateTime;

    private List<String> role;

    private Boolean enable;

    private Integer level;

    private Integer coin;

    private UserGroup group;

    private UserInfoDto userInfo;

    private WalletDto wallet;

    private static final long serialVersionUID = 1L;

    @Data
    private class UserInfoDto implements Serializable {

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
}
