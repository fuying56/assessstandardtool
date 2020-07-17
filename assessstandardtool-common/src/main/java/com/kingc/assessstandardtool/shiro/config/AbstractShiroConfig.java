package com.kingc.assessstandardtool.shiro.config;


import com.kingc.assessstandardtool.shiro.cache.SessionDAO;
import com.kingc.assessstandardtool.shiro.cache.redis.RedisCacheManager;
import com.kingc.assessstandardtool.shiro.filter.AjaxLogoutFilter;
import com.kingc.assessstandardtool.shiro.filter.CaptchaLoginAuthenticationFilter;
import com.kingc.assessstandardtool.shiro.filter.UserAjaxFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/1.
 */
public abstract class AbstractShiroConfig {

    @Value("#{ @environment['shiro.loginUrl'] ?: '/login' }")
    protected String loginUrl;

    @Value("#{ @environment['shiro.successUrl'] ?: '/' }")
    protected String successUrl;

    @Value("#{ @environment['shiro.logoutUrl'] ?: '/login' }")
    protected String logoutUrl;


    @Bean
    public DefaultWebSecurityManager securityManager(RedisTemplate redisTemplate) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        securityManager.setSessionManager(sessionManager(redisTemplate));
        return securityManager;
    }

    @Bean
    public DefaultWebSessionManager sessionManager(RedisTemplate redisTemplate) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationInterval(1800000);

        SimpleCookie simpleCookie = new SimpleCookie("jsid");
        simpleCookie.setHttpOnly(false);

        sessionManager.setSessionIdCookie(simpleCookie);
        //关闭返回的url中的session
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setCacheManager(cacheManager(redisTemplate));
        sessionManager.setSessionDAO(sessionDAO());

        return sessionManager;
    }

    @Bean
    public SessionDAO sessionDAO() {
        return new SessionDAO();
    }


    @Bean
    public RedisCacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        shiroFilterFactoryBean.setSuccessUrl(successUrl);

        shiroFilterFactoryBean.setFilters(filters());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(getFilterChain());

        return shiroFilterFactoryBean;
    }

    private Map<String, Filter> filters() {
        Map<String, Filter> filtersMap = new LinkedHashMap<>();

        AjaxLogoutFilter logoutFilter = new AjaxLogoutFilter();
        //退出登录跳转到登录页面
        logoutFilter.setRedirectUrl(logoutUrl);
        filtersMap.put("logout", logoutFilter);

        CaptchaLoginAuthenticationFilter ajaxFormAuthenticationFilter = new CaptchaLoginAuthenticationFilter();
        ajaxFormAuthenticationFilter.setLoginUrl(loginUrl);
        ajaxFormAuthenticationFilter.setPasswordParam(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM);
        ajaxFormAuthenticationFilter.setUsernameParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);

        filtersMap.put("authc", ajaxFormAuthenticationFilter);

        UserAjaxFilter userAjaxFilter = new UserAjaxFilter();
        filtersMap.put("userajax", userAjaxFilter);

        RolesAuthorizationFilter rolesAuthorizationFilter = new RolesAuthorizationFilter();
        filtersMap.put("roles", rolesAuthorizationFilter);
        return filtersMap;
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }


    protected Map<String, String> getFilterChain() {

        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<>();

        filterChainDefinitionManager.put("/error", "anon");
        filterChainDefinitionManager.put("/logout", "logout");
        filterChainDefinitionManager.put("/", "authc");
        filterChainDefinitionManager.put("/**", "authc");

        return filterChainDefinitionManager;
    }


    protected abstract AuthorizingRealm customRealm();

}
