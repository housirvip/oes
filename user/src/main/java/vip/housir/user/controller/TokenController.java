package vip.housir.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ResultResponse;
import vip.housir.user.service.CaptchaService;
import vip.housir.user.service.QiniuService;
import vip.housir.user.service.SmsService;

/**
 * @author housirvip
 */
@RestController
@RequestMapping(value = "/token")
@RequiredArgsConstructor
public class TokenController {

    private final SmsService smsService;
    private final QiniuService qiniuService;
    private final CaptchaService captchaService;

    @GetMapping(value = "/qiniu")
    public BaseResponse<String> qiniu() {

        return new ResultResponse<>(qiniuService.getToken(null));
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
