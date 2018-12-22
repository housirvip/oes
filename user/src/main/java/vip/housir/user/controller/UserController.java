package vip.housir.user.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.user.entity.User;
import vip.housir.user.service.UserService;

import java.util.Map;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/one")
    public BaseResponse<User> one() {

        return new ResultResponse<>(userService.one());
    }

    @GetMapping(value = "/detail")
    public BaseResponse<User> detail() {

        return new ResultResponse<>(userService.detail());
    }

    @GetMapping(value = "/list")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Page> list(@RequestParam Map<String, Object> param) {

        Page<User> userPage = userService.pageByParam(param);

        return new PageResponse<>(userPage, userPage.getTotal());
    }
}
