package vip.zihen.spice.common.api;
/**
 * <p>
 * REST API 响应码
 * </p>
 *
 * @author wangjie
 * @since 2021-01-05
 */
public enum ApiCode {

    SUCCESS(200, "操作成功"),
    UNAUTHORIZED(401, "非法访问"),
    NOT_PERMISSION(403, "没有权限"),
    NOT_FOUND(404, "你请求的资源不存在"),
    FAIL(500, "操作失败"),
    LOGIN_EXCEPTION(4000, "登陆失败"),
    SYSTEM_EXCEPTION(5000, "系统异常"),

    LOGIN_USER_NOT_EXIST(5001, "登陆用户不存在"),
    LOGIN_PASSWORD_EXCEPTION(5002, "登陆密码错误");


    private final int code;
    private final String message;

    ApiCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiCode getApiCode(int code) {
        ApiCode[] ecs = ApiCode.values();
        for (ApiCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
