package com.kingc.assessstandardtool.shiro.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * @author Administrator
 */
@Configuration
public class FilterChain {

    @Bean
    public FilterRegistrationBean testFilterRegistration() throws Exception {
        DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
        //该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
        delegatingFilterProxy.setTargetFilterLifecycle(false);
        delegatingFilterProxy.setTargetBeanName("shiroFilter");

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(delegatingFilterProxy);
        registrationBean.setDispatcherTypes(getDispatcherTypes());
        registrationBean.setOrder(1);
        return registrationBean;

    }


    private EnumSet<DispatcherType> getDispatcherTypes() {
        return (EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC));
    }
}
