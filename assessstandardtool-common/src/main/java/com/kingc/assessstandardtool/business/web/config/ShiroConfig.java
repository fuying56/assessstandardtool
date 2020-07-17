package com.kingc.assessstandardtool.business.web.config;

import com.kingc.assessstandardtool.shiro.cache.Constants;
import com.kingc.assessstandardtool.shiro.cache.internal.builder.HashCacheBuilder;
import com.kingc.assessstandardtool.shiro.config.AbstractShiroConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Administrator
 */
public abstract class ShiroConfig extends AbstractShiroConfig {

    @Bean
    public HashCacheBuilder hashCacheManager(RedisTemplate redisTemplate) {
        HashCacheBuilder hashCacheManager = new HashCacheBuilder(redisTemplate);
        hashCacheManager.getHashCache(Constants.USER_CACHE_NAME, Constants.SESSION_EXPIRATION_TIME);
        return hashCacheManager;
    }
}
