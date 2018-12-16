package vip.housir.base.aop;

import com.github.pagehelper.PageHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import vip.housir.base.constant.Constant;

import java.util.Map;

/**
 * @author housirvip
 */
@Aspect
@Component
public class PageHelperAop {

    @Pointcut("execution(public com.github.pagehelper.Page vip.housir.*.service.*Service.page*(..))")
    public void pageHelper() {
    }

    @Before("pageHelper()")
    public void doBefore(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0 || !(args[0] instanceof Map)) {
            return;
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) args[0];

        int pageNum = Constant.PAGE_NUM_VALUE;
        int pageSize = Constant.PAGE_SIZE_VALUE;

        if (map.containsKey(Constant.PAGE_NUM)) {
            pageNum = Integer.parseInt(map.get(Constant.PAGE_NUM).toString());
        }
        if (map.containsKey(Constant.PAGE_SIZE)) {
            pageSize = Integer.parseInt(map.get(Constant.PAGE_SIZE).toString());
        }

        PageHelper.startPage(pageNum, pageSize);
    }
}
