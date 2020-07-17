package com.kingc.assessstandardtool.business.web.config;



import com.kingc.assessstandardtool.business.base.entity.User;
import com.kingc.assessstandardtool.springmvc.config.AbstractWebAppLoggerFilter;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AbstractWebAppLoggerFilter filterRegistry = new FilterRegistry();
        registry.addInterceptor(filterRegistry).addPathPatterns("/**");
    }
}

class FilterRegistry extends AbstractWebAppLoggerFilter {

    @Override
    protected String getAccountId() {
        if (SecurityUtils.getSubject() != null && SecurityUtils.getSubject().getPrincipal() != null) {
            return ((User) SecurityUtils.getSubject().getPrincipal()).getId();
        }
        return null;
    }
}
