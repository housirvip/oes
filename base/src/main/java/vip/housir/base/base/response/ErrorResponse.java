package vip.housir.base.base.response;

import vip.housir.base.constant.Constant;

/**
 * @author housirvip
 */
public class ErrorResponse<T> extends BaseResponse<T> {

    public ErrorResponse(String message) {
        super(Constant.ERROR_CODE, message, null, null);
    }
}
