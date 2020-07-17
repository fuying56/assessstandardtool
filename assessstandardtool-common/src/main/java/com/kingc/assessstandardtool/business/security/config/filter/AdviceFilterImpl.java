package com.kingc.assessstandardtool.business.security.config.filter;

import com.kingc.assessstandardtool.spring.bean.utils.SpringContextHolder;
import com.kingc.assessstandardtool.tool.utils.UtilsString;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;
import org.heart.common.Constants;
import org.springframework.core.env.Environment;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登陆之后放入用户
 *
 * @author Administrator
 */
public class AdviceFilterImpl extends AdviceFilter {

    /**
     * 前置拦截器,用于解决两个项目间跨域问题
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Environment environment = SpringContextHolder.getBean("environment");

        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        HttpServletRequest servletRequest = (HttpServletRequest) request;

        String url = servletRequest.getRequestURL().toString().replace(servletRequest.getRequestURI(), "");
        servletRequest.setAttribute(Constants.PROJECT_URL, url);

        String jsid = WebUtils.getCleanParam(request, "jsid");

//        验证是否登陆
        if (SecurityUtils.getSubject() != null && SecurityUtils.getSubject().getPrincipal() != null) {
//            由验证中心第一次跳回来的时候会携带当前的jsid
            if (UtilsString.isNoneBlank(jsid)) {
                httpServletResponse.addCookie(new Cookie("jsid", jsid));
                WebUtils.issueRedirect(request, response, environment.getProperty("shiro.url.success"));
            }

            return true;
        }

//        jsid是null并且securityUtils==null表示未登陆
        if (UtilsString.isBlank(jsid)) {
            WebUtils.issueRedirect(request, response, environment.getProperty("shiro.url.login") + "/getCurrentUser?project_url=" + url);
        }

        return false;
    }

}
