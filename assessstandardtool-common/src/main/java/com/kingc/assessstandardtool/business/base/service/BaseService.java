package com.kingc.assessstandardtool.business.base.service;


import com.kingc.assessstandardtool.business.base.entity.User;
import com.kingc.assessstandardtool.mybatis.base.service.AbstractBaseService;

/**
 * 通用业务封装.
 *
 * @author Administrator
 */
public interface BaseService<T> extends AbstractBaseService<T> {
    /**
     * 获取当前用户.
     *
     * @return
     */
    User getCurrentUser();
}
