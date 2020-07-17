package com.kingc.assessstandardtool.shiro.cache.redis;

import com.kingc.assessstandardtool.shiro.cache.internal.HashCache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;


/**
 *
 * @author Administrator
 */
public class RedisCacheDAO<K, V> implements Cache<K, V> {

    private HashCache<K, V> hashCache;

    private long timeOut = 0;

    private RedisCacheDAO(HashCache<K, V> hashCache){
        if (hashCache == null) {
            throw new IllegalArgumentException("hashCache argument cannot be null.");
        }
        this.hashCache = hashCache ;
    }

    RedisCacheDAO(HashCache<K, V> hashCache, long timeOut){
        this(hashCache);
        this.timeOut = timeOut;
    }

    @Override
    public V get(K key) throws CacheException {
        if(key == null) {
            return null;
        }
        return this.hashCache.get(key);
    }

    @Override
    public V put(K key, V value) throws CacheException {
        if(key == null || value == null) {
            return null;
        }
        this.hashCache.put(key, value);

        return value;
    }

    @Override
    public V remove(K key) throws CacheException {
        V previous = get(key);
        this.hashCache.remove(key);
        return previous;
    }

    @Override
    public void clear() throws CacheException {
        this.hashCache.clear();
    }

    @Override
    public int size() {
        return this.hashCache.size();
    }

    @Override
    public Set<K> keys() {
        return this.hashCache.keys();
    }

    @Override
    public Collection<V> values() {
        return this.hashCache.values();
    }
}
