package vip.housir.support.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author housirvip
 */
@FeignClient(name = "qiniu-client", url = "https://www.qiniu.com/")
public interface QiniuClient {

    /**
     * 获取用户信息，不包含 UserInfo, Wallet
     *
     * @return BaseResponse
     */
    @GetMapping(value = "")
    String qiniu();
}
