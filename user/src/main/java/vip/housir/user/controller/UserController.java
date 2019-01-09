package vip.housir.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.dto.Trade;
import vip.housir.base.dto.TradeDto;
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

        return new ResultResponse<>(userService.one((Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/info")
    public BaseResponse<UserInfo> info(Authentication auth) {

        return new ResultResponse<>(userService.info((Integer) auth.getPrincipal()));
    }

    @PutMapping(value = "/info")
    public BaseResponse<Boolean> info(@RequestBody UserInfo userInfo) {

        return new ResultResponse<>(userService.update(userInfo));
    }

    @GetMapping(value = "/wallet")
    public BaseResponse<Wallet> wallet(Authentication auth) {

        return new ResultResponse<>(walletService.one((Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/detail")
    public BaseResponse<User> detail(Authentication auth) {

        return new ResultResponse<>(userService.detail((Integer) auth.getPrincipal()));
    }

    @PostMapping(value = "/payForLevel")
    public BaseResponse<Boolean> payForLevel(@RequestBody @Validated(value = Trade.class) TradeDto tradeDto, Authentication auth) {

        tradeDto.setUid((Integer) auth.getPrincipal());

        return new ResultResponse<>(walletService.payForLevel(tradeDto));
    }
}
