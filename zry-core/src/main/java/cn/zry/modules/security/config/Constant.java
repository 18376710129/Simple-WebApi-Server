package cn.zry.modules.security.config;

public class Constant {

    /**
     * 模块名称
     */
    public static final String profiles = "apis";

    /**
     * jwt
     */
    public static final String JWT_ID = "jwt";
    public static final String JWT_SECRET = "7786df7fc3a34e26a61c034d5ec8245d";
    public static final int JWT_TTL = 60 * 1000;                                //millisecond
    public static final int JWT_REFRESH_INTERVAL = 55 * 60 * 1000;              //millisecond
    public static final int JWT_REFRESH_TTL = 12 * 60 * 60 * 1000;              //millisecond

}
