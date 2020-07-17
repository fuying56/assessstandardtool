package com.kingc.assessstandardtool.shiro.cache;


import com.kingc.assessstandardtool.shiro.cache.redis.RedisCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;

/**
 * shiro回话管理.
 * @author Administrator
 */
public class SessionDAO extends AbstractSessionDAO implements CacheManagerAware {


    private Cache<Serializable, Session> sessionCache;


    private void saveSession(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            return;
        }
        session.setTimeout(Constants.SESSION_EXPIRATION_TIME * 1000);
        sessionCache.put(session.getId(), session);
    }

    @Override
    public void update(Session session) {
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            return;
        }
        sessionCache.remove(session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return sessionCache.values();
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if(sessionId == null){
            return null;
        }
        return sessionCache.get(sessionId);
    }

    @Override
    public void setCacheManager(CacheManager cacheManager) {
        //如果是使用redis 则使用自定义cache 管理类
        if (cacheManager instanceof RedisCacheManager) {
            this.sessionCache = ((RedisCacheManager)cacheManager).getCache(Constants.SESSION_CACHE_NAME, Constants.SESSION_EXPIRATION_TIME);
        } else {
            this.sessionCache = cacheManager.getCache(Constants.SESSION_CACHE_NAME);
        }
    }
}