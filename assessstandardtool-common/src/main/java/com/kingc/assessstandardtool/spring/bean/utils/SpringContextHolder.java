package com.kingc.assessstandardtool.spring.bean.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Created by Administrator on 2017/8/17 0017.
 * <p>
 * spring上下文持有者.
 */
@Component
public class SpringContextHolder implements ApplicationListener<ContextRefreshedEvent>, DisposableBean {

    private static ApplicationContext rootApplicationContext = null;

    private static ApplicationContext childApplicationContext = null;

    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * 优先从root中获取.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        T bean = (T) rootApplicationContext.getBean(name);
        if (null != bean) {
            return bean;
        }
        return (T) childApplicationContext.getBean(name);
    }


    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> Map<String, T> getBean(Class<T> requiredType) {
        assertContextInjected();
        Map<String, T> beansOfType = childApplicationContext.getBeansOfType(requiredType);
        if (0 != beansOfType.values().size()) {
            return childApplicationContext.getBeansOfType(requiredType);
        }

        return rootApplicationContext.getBeansOfType(requiredType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        if (logger.isDebugEnabled()) {
            logger.debug("清除SpringContextHolder中的rootApplicationContext:" + rootApplicationContext);
            logger.debug("清除SpringContextHolder中的childApplicationContext:" + childApplicationContext);
        }
        rootApplicationContext = null;
        childApplicationContext = null;
    }


    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        if (rootApplicationContext == null && childApplicationContext == null) {
            throw new ApplicationContextException("初始化上下文失败");
        }
    }

    /**
     * beanFactory注销时调用
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            SpringContextHolder.rootApplicationContext = event.getApplicationContext();
        } else {
            SpringContextHolder.childApplicationContext = event.getApplicationContext();
        }
    }
}