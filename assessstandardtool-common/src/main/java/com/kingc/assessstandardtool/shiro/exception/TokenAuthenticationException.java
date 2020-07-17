package com.kingc.assessstandardtool.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

public class TokenAuthenticationException extends AuthenticationException {

    /**
     * 验证码异常
     */
    private static final long serialVersionUID = 1L;

    public TokenAuthenticationException() {
        super("验证码错误。");
    }

    public TokenAuthenticationException(String message) {
        super(message);
    }

}
