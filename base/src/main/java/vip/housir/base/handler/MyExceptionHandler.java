package vip.housir.base.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ErrorResponse;

/**
 * @author housirvip
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handlerIllegalArgumentException(IllegalArgumentException ex) {

        return new ErrorResponse(ex.getMessage());
    }
}
