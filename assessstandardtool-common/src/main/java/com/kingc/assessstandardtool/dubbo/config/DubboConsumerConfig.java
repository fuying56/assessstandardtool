package com.kingc.assessstandardtool.dubbo.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * 消费者
 * @author Administrator
 */
public class DubboConsumerConfig implements EnvironmentAware {

    private RegistryConfig registryConfig;

    private ApplicationConfig applicationConfig;

    @Bean
    public ConsumerConfig consumerConfig() {
        return new ConsumerConfig();
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        return registryConfig;
    }

    @Override
    public void setEnvironment(Environment environment) {
        applicationConfig = new ApplicationConfig();
        applicationConfig.setName(environment.getProperty("dubbo.application.name"));
        registryConfig = new RegistryConfig(environment.getProperty("dubbo.registry.address"));
    }
}