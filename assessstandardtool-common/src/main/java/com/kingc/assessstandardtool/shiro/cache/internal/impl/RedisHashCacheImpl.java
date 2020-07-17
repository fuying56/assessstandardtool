package com.kingc.assessstandardtool.shiro.cache.internal.impl;



import com.kingc.assessstandardtool.shiro.cache.internal.HashCache;
import org.springframework.data.redis.core.BoundHashOperations;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * hashCache-Redis实现
 *
 * @author Administrator
 */
public class RedisHashCacheImpl<K, V> implements HashCache<K, V> {

    private BoundHashOperations boundHashOperations;

    private long timeOut = 0;

    public RedisHashCacheImpl(BoundHashOperations boundHashOperations, long timeOut) {
        this.boundHashOperations = boundHashOperations;
        this.timeOut = timeOut;
    }


    @Override
    public V get(K key) {
        return (V) boundHashOperations.get(key);
    }

    @Override
    public V put(K key, V value) {
        boundHashOperations.put(key, value);
        if (timeOut > 0) {
            boundHashOperations.expire(timeOut, TimeUnit.SECONDS);
        }
        return value;
    }

    @Override
    public void remove(K... keys) {
        boundHashOperations.delete(keys);
    }


    @Override
    public void clear() {
        if (size() != 0) {
            boundHashOperations.delete(keys().toArray());
        }
        System.out.println("清除" + boundHashOperations.getKey() + "缓存");
    }

    @Override
    public int size() {
        return boundHashOperations.size().intValue();
    }

    @Override
    public Set<K> keys() {
        return boundHashOperations.keys();
    }

    @Override
    public Collection<V> values() {
        return boundHashOperations.values();
    }

    @Override
    public Collection<K> getValues(Collection<K> keys) {
        return boundHashOperations.multiGet(keys);
    }
}
