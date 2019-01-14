package vip.housir.base.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author housirvip
 */
@FeignClient(name = "sms-client", url = "https://jyf3gwwg.api.lncld.net")
public interface SmsClient {

    /**
     * 发送短信
     *
     * @param id    String
     * @param key   String
     * @param param Map
     * @return String
     */
    @PostMapping(value = "/1.1/requestSmsCode")
    String send(@RequestHeader(name = "X-LC-Id") String id,
                @RequestHeader(name = "X-LC-Key") String key,
                @RequestBody Map<String, String> param);

    /**
     * 验证短信
     *
     * @param id    String
     * @param key   String
     * @param code  String
     * @param param Map
     * @return String
     */
    @PostMapping(value = "/1.1/verifySmsCode/{code}")
    String verify(@RequestHeader(name = "X-LC-Id") String id,
                  @RequestHeader(name = "X-LC-Key") String key,
                  @PathVariable String code,
                  @RequestBody Map<String, String> param);
}
