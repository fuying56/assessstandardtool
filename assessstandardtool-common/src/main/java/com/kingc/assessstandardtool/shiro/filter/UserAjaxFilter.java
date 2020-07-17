package com.kingc.assessstandardtool.shiro.filter;


import com.kingc.assessstandardtool.tool.utils.UtilsWeb;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/6/1.
 */
public class UserAjaxFilter extends UserFilter {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        if(UtilsWeb.isAjaxRequest((HttpServletRequest) request)){
            log.debug("ajax请求,未认证.返回失败信息,前台跳转") ;
            WebUtils.toHttp(response).setStatus(HttpStatus.UNAUTHORIZED.value());
            httpServletResponse.setHeader("Redirect-Url", getLoginUrl());
        }else{
            HttpServletRequest httpServletRequest = (HttpServletRequest) request ;
            log.debug("保存请求"+httpServletRequest.getRequestURL()) ;
            saveRequestAndRedirectToLogin(request, response) ;
        }
        return false;
    }
}
