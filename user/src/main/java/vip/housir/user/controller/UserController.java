package vip.housir.user.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.dto.PageDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.user.entity.User;
import vip.housir.user.service.UserService;

/**
 * @author housirvip
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/user")
    public BaseResponse<User> one(Authentication auth) {

        return new ResultResponse<>(userService.one((Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/user/detail")
    public BaseResponse<User> detail(Authentication auth) {

        return new ResultResponse<>(userService.detail((Integer) auth.getPrincipal()));
    }

    @GetMapping(value = "/users")
    @PreAuthorize("hasAnyRole('INSPECTOR','ADMIN','ROOT')")
    public BaseResponse<Page> list(@Validated PageDto pageDto) {

        Page<User> userPage = userService.pageByParam(pageDto);

        return new PageResponse<>(userPage, userPage.getTotal());
    }
}
