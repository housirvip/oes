package vip.housir.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.dto.Shopping;
import vip.housir.base.dto.TradeDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.store.service.TradeService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/store")
@RequiredArgsConstructor
public class StoreController {

    private final TradeService tradeService;

    @PostMapping(value = "shopping")
    public BaseResponse<Boolean> shopping(@RequestBody @Validated(value = Shopping.class) TradeDto tradeDto) {

        return new ResultResponse<>(tradeService.trade(tradeDto));
    }
}
