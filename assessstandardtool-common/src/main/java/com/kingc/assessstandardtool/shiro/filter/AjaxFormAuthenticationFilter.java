package com.kingc.assessstandardtool.shiro.filter;

import com.seed.shiro.cache.Constants;
import com.seed.shiro.http.code.HttpCode;
import com.seed.tool.utils.UtilsWeb;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 普通的登录 扩展支持ajax
 *
 * @author Administrator
 */
public class AjaxFormAuthenticationFilter extends FormAuthenticationFilter {

    public Logger log = LoggerFactory.getLogger(this.getClass());



    /**
     * 主要是针对登入成功的处理方法
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Redirect-Url",  getSuccessUrl());
        return true;
    }

    /**
     * 主要是处理登入失败的方法
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        if(e instanceof UnknownAccountException){
            SecurityUtils.getSubject().getSession().setAttribute(Constants.HTTP_CODE,HttpCode.NO_ACCOUNT);
        }else{
            SecurityUtils.getSubject().getSession().setAttribute(Constants.HTTP_CODE,HttpCode.PASSWORLD_ERROR);
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            if (UtilsWeb.isAjaxRequest(httpServletRequest)) {
                WebUtils.toHttp(response).setStatus(HttpStatus.UNAUTHORIZED.value());
                ((HttpServletResponse) response).setHeader("Redirect-Url", httpServletRequest.getContextPath() + getLoginUrl());
            } else {
                saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }
}
