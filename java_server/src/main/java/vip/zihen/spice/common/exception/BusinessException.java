package vip.zihen.spice.common.exception;

import vip.zihen.spice.common.api.ApiCode;

public class BusinessException extends Exception {

    private ApiCode code;

    public BusinessException(ApiCode apiCode) {
        super();
        this.code = apiCode;
    }

    public ApiCode getCode() {
        return code;
    }
}
