package vip.zihen.spice.config.auth.exception;

public class UnauthorizedException extends Exception {
    public final int code = 403;

    public UnauthorizedException() {
        super("权限不足");
    }
}
