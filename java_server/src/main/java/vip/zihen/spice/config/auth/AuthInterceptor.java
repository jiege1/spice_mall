package vip.zihen.spice.config.auth;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import vip.zihen.spice.common.utils.RequestUtils;
import vip.zihen.spice.config.auth.exception.UnauthorizedException;
import vip.zihen.spice.config.auth.jwt.JwtUtils;
import vip.zihen.spice.config.auth.jwt.LoginUser;
import vip.zihen.spice.workspace.user.entity.UserRole;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


@Configurable
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);

        if (loginRequired == null) {
            return true;
        }

        if (!loginRequired.required()) {
         return true;
        }

        // 未登录
        if (!JwtUtils.verifyToken(token)) {
            throw new AuthenticationException();
        }

        // todo：登陆过时 暂时未处理

        LoginUser loginUser = JwtUtils.getLoginUser(token);
        UserRole[] userRoles = loginRequired.roles();

        boolean hasRole = false;

        for (UserRole userRole: userRoles) {
            if (userRole.getRole() == loginUser.getRole()) {
                hasRole = true;
            }
        }

        // 权限不足
        if (!hasRole) {
            throw new UnauthorizedException();
        }

        // 将用户数据设置到request对象上
        request.setAttribute(RequestUtils.OPEN_ID, loginUser.getOpenId());
        request.setAttribute(RequestUtils.USER_ID, loginUser.getUserId());
        request.setAttribute(RequestUtils.ROLE, loginUser.getRole());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
