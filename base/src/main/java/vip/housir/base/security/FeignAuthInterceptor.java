package vip.housir.base.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vip.housir.base.constant.Constant;

import javax.servlet.http.HttpServletRequest;

/**
 * @author housirvip
 */
@Slf4j
public class FeignAuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.error("Feign: can't get requestAttributes");
            return;
        }

        HttpServletRequest request = attributes.getRequest();
        template.header(Constant.AUTHORIZATION, request.getHeader(Constant.AUTHORIZATION));
    }
}