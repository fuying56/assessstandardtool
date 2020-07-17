package com.kingc.assessstandardtool.shiro.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import java.io.Serializable;
import java.util.Collection;

public class ShiroSessionUtils {

    public static Collection<Session> getActivaSession() {
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
        //获取当前已登录的用户session列表
        return sessionManager.getSessionDAO().getActiveSessions();
    }

    public static void delete(Serializable sessionId) {
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();

        SessionDAO sessionDAO = sessionManager.getSessionDAO();
        Session session = sessionDAO.readSession(sessionId);

        sessionDAO.delete(session);
    }


}
