package vip.housir.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.user.entity.User;
import vip.housir.user.entity.UserInfo;
import vip.housir.user.entity.Wallet;
import vip.housir.user.service.UserService;
import vip.housir.user.service.WalletService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final WalletService walletService;

    @GetMapping(value = "")
    public BaseResponse<User> user(Authentication auth) {

        return new ResultResponse<>(userService.oneById((Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/info")
    public BaseResponse<UserInfo> info(Authentication auth) {

        return new ResultResponse<>(userService.info((Integer) auth.getPrincipal()));
    }

    @PutMapping(value = "/info")
    public BaseResponse<Integer> info(@RequestBody UserInfo userInfo, Authentication auth) {

        userInfo.setUid((Integer) auth.getPrincipal());

        return new ResultResponse<>(userService.updateByUid(userInfo));
    }

    @GetMapping(value = "/wallet")
    public BaseResponse<Wallet> wallet(Authentication auth) {

        return new ResultResponse<>(walletService.oneById((Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/detail")
    public BaseResponse<User> detail(Authentication auth) {

        return new ResultResponse<>(userService.detail((Integer) auth.getPrincipal()));
    }
}
