package cn.zry.modules.security.rest;


import cn.zry.modules.util.Encodes;

import javax.servlet.http.HttpServletRequest;

public class RestAuthUtils {

    private static final ThreadLocal<Session> threadSession = new ThreadLocal<>();

    public static void cleanThreadLocalSession() {
        threadSession.remove();
    }

    /**
     * 基于restful baseic认证模式获取认证信息
     *
     * @param request
     * @return
     * @throws RestAuthticationException
     */
    public static String[] getAuthorization(HttpServletRequest request) throws RestAuthticationException {
        String authorization = request.getHeader("authorization");
        if (authorization == null || authorization.equals("")) {
            throw new RestAuthticationException("Http header has no authorization information!");
        }

        String userPwdDomain = new String(Encodes.decodeBase64(authorization.split(" ")[1]));

        if (userPwdDomain.split(":").length < 2) {
            throw new RestAuthticationException("Authrozation information is invalid.");
        }

        return userPwdDomain.split(":");
    }


    /**
     * 基于json web token 获取token
     *
     * @param request
     * @return
     * @throws RestAuthticationException
     */
    public static String getToken(HttpServletRequest request) throws RestAuthticationException {
        String authorization = request.getHeader("authorization");
        if (authorization == null || authorization.equals("")) {
            throw new RestAuthticationException("Http header has no authorization information!");
        }
        return authorization;
    }

    public static Session getSession() {
        return threadSession.get();
    }

    public static void setSession(Session s) {
        threadSession.set(s);
    }

    public static String currentUsername() {
        Session s = getSession();
        return s != null ? s.getUsername() : null;
    }

    public static String currentUserDisplayName() {
        Session s = getSession();
        return s != null ? s.getRealName() : null;
    }

    public static String currentSid() {
        Session s = getSession();
        return s != null ? s.getSid() : null;
    }

    public static String currentUid() {
        Session s = getSession();
        return s != null ? s.getUid() : null;
    }

    public static String currentRoleId() {
        Session s = getSession();
        return s != null ? s.getRoleId() : null;
    }

    public static String currentRoleUrl() {
        Session s = getSession();
        return s != null ? s.getRoleUrl() : null;
    }
}