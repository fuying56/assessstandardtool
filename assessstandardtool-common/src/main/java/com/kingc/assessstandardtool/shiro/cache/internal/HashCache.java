package com.kingc.assessstandardtool.shiro.cache.internal;

import java.util.Collection;
import java.util.Set;

/**
 * 自定义缓存
 *
 * @param <K>
 * @param <V>
 */
public interface HashCache<K, V> {

    V get(K key);

    V put(K key, V value);

    void remove(K... keys);

    void clear();

    int size();

    Set<K> keys();

    Collection<V> values();

    Collection<K> getValues(Collection<K> keys);
}
