package vip.housir.store.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.dto.PageDto;
import vip.housir.base.dto.Shopping;
import vip.housir.base.dto.TradeDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.store.entity.Product;
import vip.housir.store.service.StoreService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping(value = "/products")
    public BaseResponse<Page> products(@Validated PageDto pageDto) {

        Page<Product> productPage = storeService.pageProductByParam(pageDto);

        return new PageResponse<>(productPage, productPage.getTotal());
    }

    @PostMapping(value = "/shopping")
    public BaseResponse<Boolean> shopping(@RequestBody @Validated(value = Shopping.class) TradeDto tradeDto, Authentication auth) {

        tradeDto.setUid((Integer) auth.getPrincipal());

        return new ResultResponse<>(storeService.trade(tradeDto));
    }
}
