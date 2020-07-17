package com.kingc.assessstandardtool.spring.bean.config;

import com.kingc.assessstandardtool.spring.bean.BeanPostProcessorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 生命周期配置文件
 *
 * @author Administrator
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public BeanPostProcessorImpl beanPostProcessor() {
        return new BeanPostProcessorImpl();
    }
}
