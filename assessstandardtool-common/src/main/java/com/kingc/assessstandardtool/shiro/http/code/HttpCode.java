package com.kingc.assessstandardtool.shiro.http.code;

import lombok.Getter;

/**
 * login-http状态码
 *
 * @author Administrator
 */

public enum HttpCode {

    /**
     * 账号不存在
     */
    NO_ACCOUNT(411),
    /**
     * 密码错误
     */
    PASSWORLD_ERROR(412),

    /**
     * 未添加签名
     */
    NO_SIGNATURE(211),
    /**
     * 含有签名且账号登陆成功
     */
    SUCCESS(210);

    HttpCode(int i) {
        this.code = i;
    }

    @Getter
    private int code;
}
