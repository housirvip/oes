package vip.housir.support.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public Boolean verify(String code) {

        String rsp = captchaClient.verify("983bd99c084d4e7647cea6a39873a4e8", code);

        return "{\"error\":0,\"res\":\"success\"}".equals(rsp);
    }
}
