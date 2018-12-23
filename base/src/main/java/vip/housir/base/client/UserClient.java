package vip.housir.base.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import vip.housir.base.dto.UserDto;
import vip.housir.base.response.BaseResponse;

/**
 * @author housirvip
 */
@FeignClient(name = "oes-user")
public interface UserClient {

    /**
     * 获取用户信息，不包含 UserInfo
     *
     * @return BaseResponse
     */
    @GetMapping(value = "/user/one")
    BaseResponse<UserDto> one();

    /**
     * 获取用户信息，包含 UserInfo
     *
     * @return BaseResponse
     */
    @GetMapping(value = "/user/detail")
    BaseResponse<UserDto> detail();
}
