package vip.housir.base.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.client.UserClient;
import vip.housir.base.dto.UserDto;
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
        BaseResponse<UserDto> userDtoBaseResponse = userClient.login(authRequest);
        UserDto userDto = userDtoBaseResponse.getResult();
        log.info(userDto.toString());
        return new ResultResponse<>(userDto);
    }
}
