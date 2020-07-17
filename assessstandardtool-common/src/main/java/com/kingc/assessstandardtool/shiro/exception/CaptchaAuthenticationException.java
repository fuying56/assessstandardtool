package com.kingc.assessstandardtool.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

public class CaptchaAuthenticationException extends AuthenticationException {

    /**
     * 验证码异常
     */
    private static final long serialVersionUID = 1L;

    public CaptchaAuthenticationException() {
        super("验证码错误。");
    }

    public CaptchaAuthenticationException(String message) {
        super(message);
    }

}
