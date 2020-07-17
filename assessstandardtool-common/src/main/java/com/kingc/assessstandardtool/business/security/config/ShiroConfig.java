package com.kingc.assessstandardtool.business.security.config;


import com.kingc.assessstandardtool.business.security.config.realm.SingleRealm;
import com.kingc.assessstandardtool.shiro.cache.Constants;
import com.kingc.assessstandardtool.shiro.cache.internal.builder.HashCacheBuilder;
import com.kingc.assessstandardtool.shiro.config.AbstractShiroConfig;
import org.apache.shiro.realm.AuthorizingRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置文件
 *
 * @author Administrator
 */
@Configuration
public class ShiroConfig extends AbstractShiroConfig {

    @Bean
    public HashCacheBuilder hashCacheBuilder(RedisTemplate redisTemplate) {
        HashCacheBuilder hashCacheBuilder = new HashCacheBuilder(redisTemplate);
        hashCacheBuilder.getHashCache(Constants.USER_CACHE_NAME, Constants.SESSION_EXPIRATION_TIME);
        return hashCacheBuilder;
    }

    @Bean
    @Override
    public AuthorizingRealm customRealm() {
        SingleRealm singleRealm = new SingleRealm();
        singleRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return singleRealm;
    }

    @Override
    protected Map<String, String> getFilterChain() {
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap();
        filterChainDefinitionManager.put("/error", "anon");
        filterChainDefinitionManager.put("/logout", "logout");
        filterChainDefinitionManager.put("/**", "anon");
        return filterChainDefinitionManager;
    }
}
