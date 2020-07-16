package com.kingc.assessstandardtool.dubbo.config;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * @author Administrator
 */
public class DubboProducerConfig extends DubboConsumerConfig {

    @Bean
    //服务提供者协议配置
    public ProtocolConfig protocol(Environment environment) {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setPort(environment.getProperty("${dubbo.protocol.port}", Integer.class));
        return protocolConfig;
    }

    @Bean
    public ProviderConfig providerConfig() {
        return new ProviderConfig();
    }
}
