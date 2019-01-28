package vip.housir.user.service.impl;

import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vip.housir.user.client.SmsClient;
import vip.housir.base.constant.Constant;
import vip.housir.user.service.SmsService;

/**
 * @author: housirvip
 */
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final SmsClient smsClient;

    @Value(value = "${sms.enable}")
    private Boolean smsEnable;

    @Override
    public Boolean send(String phone) {

        String rsp = smsClient.send(ImmutableMap.of("mobilePhoneNumber", phone));

        return Constant.SMS_OK.equals(rsp);
    }

    @Override
    public Boolean verify(String code, String phone) {

        if (!smsEnable) {
            return true;
        }

        if (code == null) {
            return false;
        }

        String rsp = smsClient.verify(code, ImmutableMap.of("mobilePhoneNumber", phone));

        return Constant.SMS_OK.equals(rsp);
    }
}
