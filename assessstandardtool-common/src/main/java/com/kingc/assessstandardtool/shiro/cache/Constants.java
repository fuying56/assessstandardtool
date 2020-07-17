package com.kingc.assessstandardtool.shiro.cache;

/**
 * 静态管理类
 */
public class Constants {
    /**
     * 客户端session中的用户的角色
     */
    public static final String SESSION_ROLE_CODE = "SESSION_ROLE_CODE";

    /**
     * session缓存过期时间
     */
    public static final long SESSION_EXPIRATION_TIME = 1800;

    /**
     * session 缓存名称
     */
    public static final String SESSION_CACHE_NAME = "shiro_redis_session";

    /**
     * session 缓存名称
     */
    public static final String USER_CACHE_NAME = "shiro_redis_user";

    /**
     * cache 默认 缓存名称
     */
    public static final String DEFAULT_NAME = "default_redis_cache:";

    //验证码参数默认名称
    public static final String SESSION_CAPTCHA_PARAM = "captchaCode";

    /**
     * 系统默认用户id
     */
    public static final String DEFAULT_USER_ID = "admin";

    /**
     * 被认证的url
     */
    public static final String authenticateUrl = "authenticateUrl";

    /**
     * 状态码
     */
    public static final String HTTP_CODE = "http_code";

}
