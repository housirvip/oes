package vip.housir.base.client;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vip.housir.base.constant.Constant;

import javax.servlet.http.HttpServletRequest;

/**
 * @author housirvip
 */
@Slf4j
public class MicroServiceConfig {

    @Bean
    public RequestInterceptor feignAuthInterceptor() {

        return requestTemplate -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                log.error("Feign: can't get requestAttributes");
                return;
            }

            log.info("feignAuthInterceptor");
            HttpServletRequest request = attributes.getRequest();
            requestTemplate.header(Constant.AUTHORIZATION, request.getHeader(Constant.AUTHORIZATION));
        };
    }
}