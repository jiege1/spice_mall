package vip.zihen.spice.common.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    public static final String OPEN_ID = "loginUserOpenId";

    public static final String USER_ID = "loginUserUserId";

    public static final String ROLE  = "loginUserRole";

    public static String getOpenId(HttpServletRequest request) {
        return (String) request.getAttribute(OPEN_ID);
    }

    public static int getUserId(HttpServletRequest request) {
        return (Integer) request.getAttribute(USER_ID);
    }

    public static int getRole(HttpServletRequest request) {
        return (Integer) request.getAttribute(ROLE);
    }
}
