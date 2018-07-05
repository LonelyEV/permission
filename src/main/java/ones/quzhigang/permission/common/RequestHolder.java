package ones.quzhigang.permission.common;

import ones.quzhigang.permission.model.SysUserModel;

import javax.servlet.http.HttpServletRequest;

public class RequestHolder {

    private static final ThreadLocal<SysUserModel> userHolder = new ThreadLocal<SysUserModel>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public static void add(SysUserModel sysUser) {
        userHolder.set(sysUser);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static SysUserModel getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }
}
