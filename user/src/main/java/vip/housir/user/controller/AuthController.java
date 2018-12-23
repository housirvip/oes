package vip.housir.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.request.Login;
import vip.housir.base.request.Register;
import vip.housir.base.request.UserRequest;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.base.utils.JwtUtils;
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
    private final JwtUtils jwtUtils;

    @PostMapping(value = "/login")
    public BaseResponse<String> login(@RequestBody @Validated(value = Login.class) UserRequest form) {

        User user = userService.login(form);

        return new ResultResponse<>(jwtUtils.encode(user.getId(), user.getRole()));
    }

    @PostMapping(value = "/register")
    public BaseResponse<String> register(@RequestBody @Validated(value = Register.class) UserRequest form) {

        User user = userService.register(form);

        return new ResultResponse<>(jwtUtils.encode(user.getId(), user.getRole()));
    }
}
