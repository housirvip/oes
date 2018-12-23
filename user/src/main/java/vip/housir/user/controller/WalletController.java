package vip.housir.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.request.Level;
import vip.housir.base.request.UserRequest;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.user.entity.Wallet;
import vip.housir.user.service.UserService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final UserService userService;

    @GetMapping(value = "/one")
    public BaseResponse<Wallet> one() {

        return new ResultResponse<>(null);
    }

    @PatchMapping(value = "/buy")
    public BaseResponse<Integer> buy(@RequestBody @Validated(value = Level.class) UserRequest userRequest) {

        Integer levelUpTo = userRequest.getLevel();

        return new ResultResponse<>(userService.userLevelUpTo(levelUpTo));
    }
}
