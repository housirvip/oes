package vip.housir.store.controller;

import com.alipay.api.AlipayApiException;
import com.github.pagehelper.Page;
import com.google.common.collect.ImmutableMap;
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
import vip.housir.store.alipay.AlipayManager;
import vip.housir.store.alipay.BizContent;
import vip.housir.store.entity.Product;
import vip.housir.store.entity.Recharge;
import vip.housir.store.service.OrderService;
import vip.housir.store.service.ProductService;
import vip.housir.store.service.RechargeService;

import java.util.Map;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/store")
@RequiredArgsConstructor
public class StoreController {

    private final OrderService orderService;
    private final ProductService productService;
    private final RechargeService rechargeService;

    private final AlipayManager alipayManager;

    @GetMapping(value = "/product/list")
    public BaseResponse<Page> products(@Validated PageDto pageDto) {

        Page<Product> productPage = productService.pageByParam(pageDto);

        return new PageResponse<>(productPage, productPage.getTotal());
    }

    @PostMapping(value = "/shopping")
    public BaseResponse<Integer> shopping(@RequestBody @Validated(value = Shopping.class) TradeDto tradeDto, Authentication auth) {

        tradeDto.setUid((Integer) auth.getPrincipal());

        return new ResultResponse<>(orderService.start(tradeDto));
    }

    @PostMapping(value = "/recharge")
    public BaseResponse<Map> recharge(@RequestBody BizContent bizContent, Authentication auth) throws AlipayApiException {

        Integer rechargeId = rechargeService.start(bizContent, (Integer) auth.getPrincipal());
        String rechargeUrl = alipayManager.payUrl(bizContent);

        return new ResultResponse<>(ImmutableMap.of("rechargeId", rechargeId, "rechargeUrl", rechargeUrl));
    }

    @GetMapping(value = "/recharge")
    public BaseResponse<Recharge> rechargeCheck(@RequestParam Integer rechargeId, Authentication auth) {

        Recharge recharge = rechargeService.oneById(rechargeId, (Integer) auth.getPrincipal());

        return new ResultResponse<>(recharge);
    }
}
