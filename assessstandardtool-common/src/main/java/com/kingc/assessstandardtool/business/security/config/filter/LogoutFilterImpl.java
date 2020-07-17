package com.kingc.assessstandardtool.business.security.config.filter;


import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 退出跳转
 *
 * @author Administrator
 */
public class LogoutFilterImpl extends org.apache.shiro.web.filter.authc.LogoutFilter {

    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        return WebUtils.toHttp(request).getRequestURL().toString().replace(WebUtils.toHttp(request).getRequestURI(), "");
    }
}
