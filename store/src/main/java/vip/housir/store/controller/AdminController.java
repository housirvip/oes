package vip.housir.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.store.entity.Product;
import vip.housir.store.service.StoreService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/store-admin")
@RequiredArgsConstructor
public class AdminController {

    private final StoreService storeService;

    @PostMapping(value = "/product")
    @PreAuthorize("hasAnyRole('ADMIN','ROOT')")
    public BaseResponse<Integer> product(@RequestBody Product product) {

        return new ResultResponse<>(storeService.productCreateOrUpdate(product));
    }
}
