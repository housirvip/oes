package vip.housir.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.request.AuthRequest;
import vip.housir.base.request.Login;
import vip.housir.base.request.Register;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ErrorMessage;
import vip.housir.base.response.ErrorResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.user.entity.User;
import vip.housir.user.service.UserService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @RequestMapping("/login")
    public BaseResponse<User> login(@RequestBody @Validated(value = Login.class) AuthRequest form) {

        return new ResultResponse<>(userService.login(form));
    }

    @RequestMapping("/register")
    public BaseResponse<User> register(@RequestBody @Validated(value = Register.class) AuthRequest form) {

        return new ResultResponse<>(userService.register(form));
    }
}
