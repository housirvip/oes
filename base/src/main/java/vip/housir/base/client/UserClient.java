package vip.housir.base.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import vip.housir.base.dto.UserDto;
import vip.housir.base.dto.WalletDto;
import vip.housir.base.response.BaseResponse;

/**
 * @author housirvip
 */
@FeignClient(name = "oes-user", configuration = MicroServiceConfig.class)
public interface UserClient {

    /**
     * 获取用户信息，不包含 UserInfo, Wallet
     *
     * @return BaseResponse
     */
    @GetMapping(value = "/user")
    BaseResponse<UserDto> user();

    /**
     * 获取用户钱包信息
     *
     * @return BaseResponse
     */
    @GetMapping(value = "/user/wallet")
    BaseResponse<WalletDto> wallet();

    /**
     * 获取用户信息，包含 UserInfo, Wallet
     *
     * @return BaseResponse
     */
    @GetMapping(value = "/user/detail")
    BaseResponse<UserDto> detail();
}
