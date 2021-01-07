package vip.zihen.spice.common.exceptionHandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.zihen.spice.common.api.ApiCode;
import vip.zihen.spice.common.api.ApiResult;

import javax.naming.AuthenticationException;
import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(value = AuthException.class)
    @ResponseBody
    public ApiResult authExceptionHandler(AuthException e){
        System.out.println("未知异常！原因是:"+e);
        return ApiResult.fail(ApiCode.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public ApiResult authenticationException(AuthenticationException e){
        System.out.println("未知异常！原因是:"+e);
        return ApiResult.fail(ApiCode.NOT_PERMISSION);
    }
}
