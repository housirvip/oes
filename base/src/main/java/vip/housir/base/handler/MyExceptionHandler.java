package vip.housir.base.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.mq.DingSender;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ErrorResponse;

/**
 * @author housirvip
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class MyExceptionHandler {

    private final DingSender dingSender;

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse handlerRuntimeException(RuntimeException ex) {

        if (ex.getMessage() == null || ex.getMessage().isEmpty()) {

            dingSender.exception(ex);
            return new ErrorResponse(ErrorMessage.SERVICE_EXCEPTION);
        }

        return new ErrorResponse(ex.getMessage());
    }
}
