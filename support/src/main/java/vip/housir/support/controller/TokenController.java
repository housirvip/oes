package vip.housir.support.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.support.service.CaptchaService;
import vip.housir.support.service.QiniuService;
import vip.housir.support.service.SmsService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/token")
@RequiredArgsConstructor
public class TokenController {

    private final QiniuService qiniuService;
    private final SmsService smsService;
    private final CaptchaService captchaService;

    @GetMapping(value = "/qiniu")
    public BaseResponse<String> qiniu(@RequestParam String name) {

        return new ResultResponse<>(qiniuService.getToken(name));
    }

    @GetMapping(value = "/sms/{phone}")
    public BaseResponse<Boolean> sms(@PathVariable String phone) {

        return new ResultResponse<>(smsService.send(phone));
    }

    @GetMapping(value = "/sms/{phone}/{code}")
    public BaseResponse<Boolean> sms(@PathVariable String phone, @PathVariable String code) {

        return new ResultResponse<>(smsService.verify(code, phone));
    }

    @GetMapping(value = "/captcha/{code}")
    public BaseResponse<Boolean> captcha(@PathVariable String code) {

        return new ResultResponse<>(captchaService.verify(code));
    }
}
