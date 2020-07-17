package com.kingc.assessstandardtool.shiro.filter;


import com.kingc.assessstandardtool.tool.utils.UtilsWeb;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxLogoutFilter extends LogoutFilter {

    @Override
    protected void issueRedirect(ServletRequest request, ServletResponse response, String redirectUrl) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (UtilsWeb.isAjaxRequest(httpServletRequest)) {
            WebUtils.toHttp(response).setStatus(HttpStatus.UNAUTHORIZED.value());
            ((HttpServletResponse) response).setHeader("Redirect-Url", httpServletRequest.getContextPath() + getRedirectUrl());
        } else {
            super.issueRedirect(request, response, redirectUrl);
        }
    }
}
