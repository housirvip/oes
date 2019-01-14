package vip.housir.support.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author housirvip
 */
@FeignClient(name = "qiniu-client", url = "https://api.github.com")
public interface QiniuClient {

    /**
     * 获取用户信息，不包含 UserInfo, Wallet
     *
     * @return BaseResponse
     */
    @GetMapping(value = "/search/repositories")
    String searchRepo(@RequestParam String q);
}
