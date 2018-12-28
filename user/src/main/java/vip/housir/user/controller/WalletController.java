package vip.housir.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.dto.Trade;
import vip.housir.base.dto.TradeDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.user.entity.Wallet;
import vip.housir.user.service.WalletService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping
    public BaseResponse<Wallet> one(Authentication auth) {

        return new ResultResponse<>(walletService.one((Integer) auth.getPrincipal()));
    }

    @PostMapping(value = "/trade")
    public BaseResponse<Boolean> trade(@RequestBody @Validated(value = Trade.class) TradeDto tradeDto, Authentication auth) {

        tradeDto.setUid((Integer) auth.getPrincipal());

        return new ResultResponse<>(walletService.trade(tradeDto));
    }
}
