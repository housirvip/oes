package vip.housir.support.service.impl;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.housir.base.client.SmsClient;
import vip.housir.support.service.SmsService;

/**
 * @author: housirvip
 */
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final SmsClient smsClient;

    @Value("${lc.id}")
    private String lcId;

    @Value("${lc.key}")
    private String lcKey;

    @Override
    public Boolean send(String phone) {

        String rsp = smsClient.send(lcId, lcKey, ImmutableMap.of("mobilePhoneNumber", phone));

        return "{}".equals(rsp);
    }

    @Override
    public Boolean verify(String code, String phone) {

        String rsp = smsClient.verify(lcId, lcKey, code, ImmutableMap.of("mobilePhoneNumber", phone));

        return "{}".equals(rsp);
    }
}
