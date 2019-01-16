package vip.housir.user.service;

/**
 * @author housirvip
 */
public interface CaptchaService {

    /**
     * 验证验证码
     *
     * @param code String
     * @return Boolean
     */
    Boolean verify(String code);
}
