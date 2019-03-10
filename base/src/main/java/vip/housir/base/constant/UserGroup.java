package vip.housir.base.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum UserGroup {

    /**
     * Primary
     */
    Primary("primary"),
    /**
     * User
     */
    User("user"),
    /**
     * ChengGong
     */
    ChengGong("cheng_gong"),
    /**
     * Vip
     */
    Vip("vip"),
    /**
     * Admin
     */
    Admin("admin"),
    /**
     * Root
     */
    Root("root");

    private String value;
}
