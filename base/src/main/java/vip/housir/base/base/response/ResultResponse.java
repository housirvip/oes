package vip.housir.base.base.response;

import vip.housir.base.constant.Constant;

/**
 * @author housirvip
 */
public class ResultResponse<T> extends BaseResponse<T> {

    public ResultResponse(T result) {
        super(Constant.SUCCESS_CODE, null, null, result);
    }
}
