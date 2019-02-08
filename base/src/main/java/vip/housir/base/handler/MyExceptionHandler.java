package vip.housir.base.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vip.housir.base.constant.ErrorMessage;
import vip.housir.base.dto.DingDto;
import vip.housir.base.response.BaseResponse;
import vip.housir.base.response.ErrorResponse;

/**
 * @author housirvip
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
@EnableBinding(value = LogOutput.class)
public class MyExceptionHandler {

    private final LogOutput logOutput;

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse handlerRuntimeException(RuntimeException ex) {

        if (ex.getMessage() == null || ex.getMessage().isEmpty()) {

            DingDto dingDto = new DingDto().javaException(ex.toString(), ex.getStackTrace()[0].toString());
            logOutput.log().send(MessageBuilder.withPayload(dingDto).build());
            log.error(ErrorMessage.SERVICE_EXCEPTION, ex);
            return new ErrorResponse(ErrorMessage.SERVICE_EXCEPTION);
        }

        return new ErrorResponse(ex.getMessage());
    }
}
