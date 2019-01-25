package vip.housir.base.client;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.housir.base.constant.Constant;

/**
 * @author housirvip
 */
@FeignClient(name = "captcha-client", url = "${captcha.url}", configuration = CaptchaClient.Configuration.class)
public interface CaptchaClient {

    /**
     * 验证验证码
     *
     * @param code String
     * @return String
     */
    @PostMapping(value = "/api/site_verify")
    String verify(@RequestParam(name = "response") String code);

    @Slf4j
    class Configuration {

        @Bean
        public RequestInterceptor captchaInterceptor(@Value(value = "${captcha.key}") String key) {

            return requestTemplate -> requestTemplate.query(Constant.CAPTCHA_KEY, key);
        }
    }
}
