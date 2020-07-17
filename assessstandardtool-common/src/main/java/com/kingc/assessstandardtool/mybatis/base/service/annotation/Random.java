package com.kingc.assessstandardtool.mybatis.base.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Random {

    /**
     * 随机生成长度.
     *
     * @return
     */
    int length() default 32;

}
