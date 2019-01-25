package vip.housir.base.client;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vip.housir.base.constant.Constant;

import java.util.Map;

/**
 * @author housirvip
 */
@FeignClient(name = "sms-client", url = "${sms.url}", configuration = SmsClient.Configuration.class)
public interface SmsClient {

    /**
     * 发送短信
     *
     * @param param Map
     * @return String
     */
    @PostMapping(value = "/1.1/requestSmsCode")
    String send(@RequestBody Map<String, String> param);

    /**
     * 验证短信
     *
     * @param code  String
     * @param param Map
     * @return String
     */
    @PostMapping(value = "/1.1/verifySmsCode/{code}")
    String verify(@PathVariable String code,
                  @RequestBody Map<String, String> param);

    class Configuration {

        @Bean
        public RequestInterceptor smsInterceptor(
                @Value(value = "${sms.id}") String lcId,
                @Value(value = "${sms.key}") String lcKey) {

            return requestTemplate -> {
                requestTemplate.header(Constant.SMS_ID, lcId);
                requestTemplate.header(Constant.SMS_KEY, lcKey);
            };
        }
    }
}
