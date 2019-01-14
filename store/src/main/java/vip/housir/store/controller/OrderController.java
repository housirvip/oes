package vip.housir.store.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.dto.PageDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.store.entity.Order;
import vip.housir.store.service.OrderService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "/{id}")
    public BaseResponse<Order> order(@PathVariable Integer id, Authentication auth) {

        return new ResultResponse<>(orderService.oneById(id, (Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/list")
    public BaseResponse<Page> orders(@Validated PageDto pageDto, Authentication auth) {

        return new ResultResponse<>(orderService.pageByParam(pageDto.putUid((Integer) auth.getPrincipal())));
    }
}
