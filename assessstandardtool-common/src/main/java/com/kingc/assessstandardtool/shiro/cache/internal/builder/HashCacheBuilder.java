package com.kingc.assessstandardtool.shiro.cache.internal.builder;


import com.kingc.assessstandardtool.shiro.cache.internal.HashCache;
import com.kingc.assessstandardtool.shiro.cache.internal.impl.RedisHashCacheImpl;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * @author Administrator
 */
public class HashCacheBuilder {

    private RedisTemplate redisTemplate;

    private ConcurrentMap<String, HashCache> hashCaches = new ConcurrentHashMap<String, HashCache>();

    public HashCacheBuilder(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public <HK, HV> HashCache<HK, HV> getHashCache(String cacheName) {
        return this.getHashCache(cacheName, 0);
    }

    public <HK, HV> HashCache<HK, HV> getHashCache(String cacheName, long timeOut) {
        HashCache<HK, HV> cache = hashCaches.get(cacheName);
        if (null == cache) {
            cache = new RedisHashCacheImpl<HK, HV>(redisTemplate.boundHashOps(cacheName), timeOut);
            hashCaches.put(cacheName, cache);
        }
        return cache;
    }

}
