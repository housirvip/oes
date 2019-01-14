package vip.housir.base.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author housirvip
 */
@FeignClient(name = "captcha-client", url = "https://captcha.luosimao.com")
public interface CaptchaClient {

    /**
     * 验证验证码
     *
     * @param key  String
     * @param code String
     * @return String
     */
    @PostMapping(value = "/api/site_verify")
    String verify(@RequestParam(name = "api_key") String key,
                  @RequestParam(name = "response") String code);
}
