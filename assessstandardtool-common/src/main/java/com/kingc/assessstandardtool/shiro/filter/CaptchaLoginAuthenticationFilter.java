package com.kingc.assessstandardtool.shiro.filter;


import com.kingc.assessstandardtool.shiro.cache.Constants;
import com.kingc.assessstandardtool.shiro.exception.CaptchaAuthenticationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 扩展支持验证码
 * @author Administrator
 */
public class CaptchaLoginAuthenticationFilter extends AjaxFormAuthenticationFilter {

    private String captcha = "captcha";

    private boolean enableCaptcha = false;

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpSession session = ((HttpServletRequest) request).getSession();

        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        //首先验证验证码是否正确
        if (enableCaptcha && StringUtils.isNotBlank(getCaptchaSession(request)) && !getCaptchaSession(request).equals(getCaptcha(request))) {
            return onLoginFailure(token, new CaptchaAuthenticationException(), request, response);
        }

        try {
            Subject subject = getSubject(request, response);
            subject.login(token);
            //登陆成功之后去除session信息
            session.removeAttribute("captcha");
            session.removeAttribute("errorCount");
            //继续执行处理登陆成功的处理
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    /**
     * 获取session中存储的验证码参数值
     *
     * @param request
     * @return
     */
    protected String getCaptchaSession(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Object captchaSession = httpServletRequest.getSession().getAttribute(Constants.SESSION_CAPTCHA_PARAM);
        if (captchaSession != null) {
            return ((String) captchaSession).trim().toUpperCase();
        }
        return null;
    }

    /**
     * 获取request请求中的验证码参数值
     *
     * @param request
     * @return
     */
    protected String getCaptcha(ServletRequest request) {
        String captcha = request.getParameter(getCaptcha());
        return captcha != null ? captcha.trim().toUpperCase() : null;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public void setEnableCaptcha(boolean enableCaptcha) {
        this.enableCaptcha = enableCaptcha;
    }
}
