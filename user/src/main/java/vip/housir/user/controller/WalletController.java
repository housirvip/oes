package vip.housir.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.request.WalletRequest;
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

    @GetMapping(value = "/one")
    public BaseResponse<Wallet> one() {

        return new ResultResponse<>(null);
    }

    @PostMapping(value = "/shopping")
    public BaseResponse<Boolean> shopping(@RequestBody WalletRequest walletRequest) {

        return new ResultResponse<>(walletService.shopping(walletRequest.getProduct()));
    }
}
