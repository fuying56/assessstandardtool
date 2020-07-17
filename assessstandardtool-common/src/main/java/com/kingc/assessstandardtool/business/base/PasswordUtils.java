package com.kingc.assessstandardtool.business.base;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 密码加密工具类
 *
 * @author Administrator
 */
public class PasswordUtils {
    public static String md5(String str) {
        return new Md5Hash(str, "kingc").toString();
    }
}
