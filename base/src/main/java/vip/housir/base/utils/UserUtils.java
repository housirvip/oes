package vip.housir.base.utils;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author housirvip
 */
public class UserUtils {

    public static Integer uid() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof Integer) {
            return (Integer) principal;
        }

        return null;
    }
}
