package vip.housir.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.housir.base.client.CaptchaClient;
import vip.housir.base.constant.Constant;
import vip.housir.user.service.CaptchaService;

/**
 * @author: housirvip
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

    private final CaptchaClient captchaClient;

    @Value(value = "${captcha.enable}")
    private Boolean captchaEnable;


    @Override
    public Boolean verify(String code) {

        if (!captchaEnable) {
            return true;
        }

        if (code == null) {
            return false;
        }

        String rsp = captchaClient.verify(code);

        return Constant.CAPTCHA_OK.equals(rsp);
    }
}
