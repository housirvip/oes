package vip.housir.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.dto.Login;
import vip.housir.base.dto.Register;
import vip.housir.base.dto.UserDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.user.service.UserService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/login")
    public BaseResponse<String> login(@RequestBody @Validated(value = Login.class) UserDto userDto) {

        return new ResultResponse<>(userService.login(userDto));
    }

    @PostMapping(value = "/register")
    public BaseResponse<String> register(@RequestBody @Validated(value = Register.class) UserDto userDto) {

        return new ResultResponse<>(userService.register(userDto));
    }

    @PostMapping(value = "/register")
    public BaseResponse<String> register(Authentication auth) {

        return new ResultResponse<>(userService.refresh((Integer) auth.getPrincipal()));
    }
}
