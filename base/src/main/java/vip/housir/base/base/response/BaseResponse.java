package vip.housir.base.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author housirvip
 */
@Data
@AllArgsConstructor
public class BaseResponse<T> {

    private Integer code;

    private String message;

    private Long total;

    private T result;
}
