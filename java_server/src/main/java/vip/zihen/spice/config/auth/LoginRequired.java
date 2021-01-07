package vip.zihen.spice.config.auth;

import vip.zihen.spice.workspace.user.entity.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
    boolean required() default true;
    UserRole[] roles() default {UserRole.CALLER, UserRole.USER};
}
