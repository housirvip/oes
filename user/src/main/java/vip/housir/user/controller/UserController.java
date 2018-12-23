package vip.housir.user.controller;

import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.request.Level;
import vip.housir.base.request.PageRequest;
import vip.housir.base.request.UserRequest;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.PageResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.user.entity.User;
import vip.housir.user.service.UserService;

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

    @PatchMapping(value = "/levelUp")
    public BaseResponse<Integer> levelUp(@RequestBody @Validated(value = Level.class) UserRequest userRequest) {

        Integer levelUpTo = userRequest.getLevel();

        return new ResultResponse<>(userService.userLevelUpTo(levelUpTo));
    }

    @GetMapping(value = "/list")
    @PreAuthorize("hasAnyRole('INSPECTOR','ADMIN','ROOT')")
    public BaseResponse<Page> list(@Validated PageRequest pageRequest) {

        Page<User> userPage = userService.pageByParam(pageRequest);

        return new PageResponse<>(userPage, userPage.getTotal());
    }
}
