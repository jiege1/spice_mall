package vip.zihen.spice.common.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.zihen.spice.common.api.ApiCode;
import vip.zihen.spice.common.api.ApiResult;
import vip.zihen.spice.common.exception.BusinessException;

import javax.naming.AuthenticationException;
import javax.security.auth.message.AuthException;

@ControllerAdvice
public class ExceptionHandler {

    /**
     * 403
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = AuthException.class)
    @ResponseBody
    public ApiResult authExceptionHandler(AuthException e){
        System.out.println("权限不足！原因是:"+e);
        return ApiResult.fail(ApiCode.NOT_PERMISSION);
    }

    /**
     * 401
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public ApiResult authenticationException(AuthenticationException e){
        System.out.println("未登录！原因是:"+e);
        return ApiResult.fail(ApiCode.UNAUTHORIZED);
    }

    /**
     * 业务错误
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ApiResult authExceptionHandler(BusinessException e){
        System.out.println("业务错误！原因是:"+e);
        return ApiResult.fail(e.getCode());
    }
}
