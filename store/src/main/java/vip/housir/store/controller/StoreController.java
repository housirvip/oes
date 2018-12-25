package vip.housir.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.request.Shopping;
import vip.housir.base.request.WalletRequest;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.store.service.OrderService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/store")
@RequiredArgsConstructor
public class StoreController {

    private final OrderService orderService;

    @PostMapping(value = "shopping")
    public BaseResponse<Boolean> shopping(@RequestBody @Validated(value = Shopping.class) WalletRequest form) {

        return new ResultResponse<>(orderService.create(form));
    }
}
