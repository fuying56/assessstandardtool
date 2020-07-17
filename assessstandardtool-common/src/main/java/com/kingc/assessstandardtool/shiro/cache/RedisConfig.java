package com.kingc.assessstandardtool.shiro.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 主要是为了修改redis的序列化问题
 */
@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String, byte[]> reloadRedisTemplate(RedisTemplate redisTemplate) {
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);

        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);

        return redisTemplate;
    }
}
