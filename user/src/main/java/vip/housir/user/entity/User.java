package vip.housir.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author housirvip
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String email;

    private String phone;

    private String password;

    private Date createTime;

    private Date updateTime;

    private List<String> role;

    private Boolean enable;

    private static final long serialVersionUID = 1L;
}