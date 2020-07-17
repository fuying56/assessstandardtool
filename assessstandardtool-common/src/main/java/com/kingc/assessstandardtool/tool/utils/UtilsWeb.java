package com.kingc.assessstandardtool.tool.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * 类描述：    响应内容对象 	ajax强制使用该类作为返回对象，其他方式选择性使用
 * 创建人：LLB
 * 创建时间：2015年6月28日 下午4:44:36
 */
public class UtilsWeb extends HashMap<String, Object> {

    private final static String[] agent = {"Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser"};

    /**
     * 判断User-Agent 是不是来自于手机
     *
     * @param ua
     * @return
     */
    public static boolean checkAgentIsMobile(String ua) {
        boolean flag = false;
        if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
            // 排除 苹果桌面系统
            if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
                for (String item : agent) {
                    if (ua.contains(item)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 判断HTTP请求是否ajax请求
     *
     * @param request http请求对象
     * @return true或false
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * 为url添加请求上下文地址
     *
     * @param request http请求
     * @param url     需要包装上下文路径的url
     * @return
     */
    public static String wrapContextPath(HttpServletRequest request, String url) {
        if (StringUtils.isBlank(url)) {
            return request.getContextPath();
        }

        if (url.startsWith(request.getContextPath())) {
            return url;
        }

        url = request.getContextPath() + "/" + url;

        url = url.replaceAll("/+", "/");
        //如果是跟路径，默认不加前缀符号
        if ("/".equals(url)) {
            url = "";
        }

        return url;
    }

    /**
     * 消除url中的请求上下文路径
     *
     * @param request http请求
     * @param url     http请求的url
     * @return
     */
    public static String unwrapContextPath(HttpServletRequest request, String url) {
        if (StringUtils.isBlank(url) || !url.startsWith(request.getContextPath())) {
            return url;
        }

        if (!"/".equals(request.getContextPath())) {
            url = url.substring(request.getContextPath().length());
        }

        return url.replaceAll("/+", "/");
    }

    /**
     * content转换为json，并使用response返回内容
     *
     * @param response
     * @param content
     */
    public static void writeToResponse(HttpServletResponse response, Object content) {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
        try {
            response.setContentType(MediaType.APPLICATION_JSON.getType());
            out = response.getWriter();
            out.println(JSON.toJSONString(content));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * responseEntity内容转换为json，并使用response返回内容
     *
     * @param response
     * @param responseEntity
     */
    public static void writeToResponse(HttpServletResponse response, ResponseEntity<?> responseEntity) {
        response.setStatus(responseEntity.getStatusCode().value());
        writeToResponse(response, responseEntity.getBody());
    }
}

