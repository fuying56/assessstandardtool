package com.kingc.assessstandardtool.shiro.cache.redis;


import com.kingc.assessstandardtool.shiro.cache.Constants;
import com.kingc.assessstandardtool.shiro.cache.internal.HashCache;
import com.kingc.assessstandardtool.shiro.cache.internal.builder.HashCacheBuilder;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * @author Administrator
 * @decsription: 缓存管理器
 */
public class RedisCacheManager implements CacheManager {

    private RedisTemplate<String, byte[]> redisTemplate;

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();

    public RedisCacheManager(RedisTemplate<String, byte[]> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return this.getCache(Constants.DEFAULT_NAME + name, 0);
    }

    public <K, V> Cache<K, V> getCache(String name, long timeOut) throws CacheException {
        Cache cache = caches.get(name);
        if (cache == null) {

            HashCache<K, V> hashCache = new HashCacheBuilder(redisTemplate).getHashCache(name, timeOut);
            cache = new RedisCacheDAO<>(hashCache, timeOut);

            caches.put(name, cache);
        }
        return caches.get(name);
    }
}