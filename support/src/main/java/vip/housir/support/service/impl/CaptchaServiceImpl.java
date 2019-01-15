package vip.housir.support.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.housir.base.client.CaptchaClient;
import vip.housir.support.service.CaptchaService;

/**
 * @author: housirvip
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

    private final CaptchaClient captchaClient;

    @Value("${captcha.key}")
    private String key;

    @Override
    public Boolean verify(String code) {

        String rsp = captchaClient.verify(key, code);

        return "{\"error\":0,\"res\":\"success\"}".equals(rsp);
    }
}
