package vip.housir.support.client;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vip.housir.base.constant.Constant;
import vip.housir.base.dto.DingDto;

/**
 * @author housirvip
 */
@FeignClient(name = "ding-client", url = "${ding.url}", configuration = DingClient.Configuration.class)
public interface DingClient {

    /**
     * 验证验证码
     *
     * @param dingDto DingDto
     * @return String
     */
    @PostMapping(value = "/robot/send")
    String send(@RequestBody DingDto dingDto);

    @Slf4j
    class Configuration {

        @Bean
        public RequestInterceptor dingInterceptor(@Value(value = "${ding.key}") String key) {

            return requestTemplate -> requestTemplate.query(Constant.DING_KEY, key);
        }
    }
}
