package com.kingc.assessstandardtool.shiro.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.io.IOException;
import java.util.Properties;

/**
 * 将应用的properties 添加到应用环境之中
 *
 * @author wuhulala
 * @version 1.0
 * @since 2018/1/21
 */
public class EnvironmentPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(EnvironmentPlaceholderConfigurer.class);

    private Environment environment;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        super.postProcessBeanFactory(beanFactory);

        try {
            Properties proper = new Properties();
            proper.setProperty("shiro.url.login","/login");
            proper.setProperty("shiro.url.success","/index");
            proper.setProperty("shiro.user.usernameParam","username");
            proper.setProperty("shiro.user.passwordParam","password");
            proper.setProperty("shiro.user.concurrency.count","1");

            this.localProperties = new Properties[]{proper};
            PropertySource<?> localPropertySource = new PropertiesPropertySource(LOCAL_PROPERTIES_PROPERTY_SOURCE_NAME, mergeProperties());

            ((StandardEnvironment) environment).getPropertySources().addLast(localPropertySource);

        } catch (IOException e) {
            logger.error("add properties to environment error, please check your config file !!!", e);
        }

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}