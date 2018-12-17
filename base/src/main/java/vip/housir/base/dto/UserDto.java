package vip.housir.base.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class UserDto implements Serializable {
    private Integer id;

    private String username;

    private String email;

    private String phone;

    private Date createTime;

    private Date updateTime;

    private List<String> role;

    private Boolean enable;

    private Integer level;

    private String group;

    private UserInfoDto userInfo;

    private static final long serialVersionUID = 1L;
}
