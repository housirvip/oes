package vip.housir.store.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import vip.housir.base.utils.JsonUtils;

import java.util.Map;

/**
 * @author housirvip
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AlipayManager {

    private final AlipayClient alipayClient;

    @Value("${alipay.notify-url}")
    private String notifyUrl;

    @Value("${alipay.alipay-public-key}")
    private String alipayPublicKey;

    @Value("${alipay.charset}")
    private String charset;

    @Value("${alipay.sign-type}")
    private String signType;

    public String payUrl(BizContent bizContent) throws AlipayApiException {

        //创建API对应的request
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
        //在公共参数中设置回跳和通知地址
        alipayRequest.setNotifyUrl(notifyUrl);
        alipayRequest.setBizContent(JsonUtils.convertToString(bizContent));

        return alipayClient.execute(alipayRequest).getQrCode();
    }

    public Boolean verify(Map<String, String> param) throws AlipayApiException {

        param.remove("sign_type");
        return AlipaySignature.rsaCheckV2(param, alipayPublicKey, charset, signType);
    }
}
