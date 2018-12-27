package vip.housir.base.aop;

import com.github.pagehelper.PageHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import vip.housir.base.constant.Constant;
import vip.housir.base.request.PageRequest;

/**
 * @author housirvip
 */
@Aspect
@Component
public class PageHelperAop {

    @Before("execution(public com.github.pagehelper.Page vip.housir.*.service.*Service.page*(..))")
    public void doBefore(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0 || !(args[0] instanceof PageRequest)) {
            return;
        }

        PageRequest pageRequest = (PageRequest) args[0];

        int pageNum = pageRequest.getPageNum() == null ? Constant.PAGE_NUM_VALUE : pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize() == null ? Constant.PAGE_SIZE_VALUE : pageRequest.getPageSize();

        PageHelper.startPage(pageNum, pageSize);
    }
}
