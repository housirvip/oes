package vip.housir.base.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ErrorResponse;

/**
 * @author housirvip
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse handlerRuntimeException(RuntimeException ex) {

        if (ex instanceof NullPointerException) {
            log.error("handlerRuntimeException", ex);
        }

        return new ErrorResponse(ex.getMessage());
    }
}
