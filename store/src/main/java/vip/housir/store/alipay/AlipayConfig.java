package vip.housir.store.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author housirvip
 */
@Slf4j
@Configuration
public class AlipayConfig {

    @Value("${alipay.server-url}")
    private String serverUrl;

    @Value("${alipay.app-id}")
    private String appId;

    @Value("${alipay.private-key}")
    private String privateKey;

    @Value("${alipay.alipay-public-key}")
    private String alipayPublicKey;

    @Value("${alipay.format}")
    private String format;

    @Value("${alipay.charset}")
    private String charset;

    @Value("${alipay.sign-type}")
    private String signType;

    @Bean
    public AlipayClient alipayClient() {

        //获得初始化的 alipay Client
        return new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
    }
}
