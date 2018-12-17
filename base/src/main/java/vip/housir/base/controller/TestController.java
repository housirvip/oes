package vip.housir.base.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.client.UserClient;
import vip.housir.base.request.AuthRequest;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final UserClient userClient;

    @RequestMapping("/one")
    public BaseResponse list(@RequestBody AuthRequest authRequest) {

        log.info(authRequest.toString());
        return new ResultResponse<>(userClient.login(authRequest));
    }
}
