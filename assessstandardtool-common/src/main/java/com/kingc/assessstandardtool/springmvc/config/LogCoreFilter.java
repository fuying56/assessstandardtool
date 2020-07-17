package com.kingc.assessstandardtool.springmvc.config;


import com.kingc.assessstandardtool.springmvc.http.BodyReaderHttpServletRequestWrapper;
import com.kingc.assessstandardtool.springmvc.http.HttpHelper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Administrator
 */
@Configuration
public  class LogCoreFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        String json = HttpHelper.getBodyString(requestWrapper);
        filterChain.doFilter(requestWrapper, response);
    }
}