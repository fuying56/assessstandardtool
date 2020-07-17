package com.kingc.assessstandardtool.springmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * web.xml配置
 *
 * @author Administrator
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new HandlerInterceptorImpl());
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        // TODO: 2018/7/31 0031 暂时先这样写
        registry.addViewController("/").setViewName("redirect:/index");
        super.addViewControllers(registry);
    }
}