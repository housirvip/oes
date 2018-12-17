package vip.housir.base.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vip.housir.base.request.AuthRequest;
import vip.housir.base.response.BaseResponse;

/**
 * @author housirvip
 */
@FeignClient(name = "oes-user")
public interface UserClient {

    /**
     * 登录
     *
     * @param authRequest AuthRequest
     * @return BaseResponse
     */
    @PostMapping(value = "/auth/login")
    BaseResponse login(@RequestBody AuthRequest authRequest);

    /**
     * 注册
     *
     * @param authRequest AuthRequest
     * @return BaseResponse
     */
    @PostMapping(value = "/auth/register")
    BaseResponse register(@RequestBody AuthRequest authRequest);
}
