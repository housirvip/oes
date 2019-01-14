package vip.housir.support.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.support.service.QiniuService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/token")
@RequiredArgsConstructor
public class TokenController {

    private final QiniuService qiniuService;

    @GetMapping(value = "/qiniu")
    public BaseResponse<String> git() {

        return new ResultResponse<>(qiniuService.simpleToken());
    }
}
