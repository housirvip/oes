package vip.housir.base.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author housirvip
 */
@Data
public class UserRequest implements Serializable {

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

    @NotNull(groups = {Level.class})
    private Integer level;

    private Boolean remember;

    private static final long serialVersionUID = 1L;
}