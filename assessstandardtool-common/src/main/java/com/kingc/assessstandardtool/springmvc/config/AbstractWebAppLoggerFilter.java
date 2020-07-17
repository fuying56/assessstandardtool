package com.kingc.assessstandardtool.springmvc.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.kingc.assessstandardtool.tool.utils.UtilsDate;
import com.kingc.assessstandardtool.tool.utils.UtilsString;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * @author Administrator
 */
public abstract class AbstractWebAppLoggerFilter implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(AbstractWebAppLoggerFilter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuilder logRecord = new StringBuilder();
        String accountId = getAccountId();
        if (UtilsString.isNoneBlank(accountId)) {
            logRecord.append("账号id(来自租户表或者基础表):");
            logRecord.append(accountId);
            logRecord.append(",");
        }
        logRecord.append("时间:");
        logRecord.append(UtilsDate.currentDateTime());
        logRecord.append(",");

        logRecord.append("请求路径:");
        logRecord.append(request.getRequestURI());
        logRecord.append(",");

        logRecord.append("form||url提交参数:");
        logRecord.append(JSON.toJSON(request.getParameterMap()));
        logRecord.append(",");


        String jsonParam = reader(request.getInputStream());
        if (UtilsString.isNoneBlank(jsonParam)) {
            logRecord.append("json提交参数:");
            logRecord.append(jsonParam);
        }
        log.info(logRecord.toString());

        return true;
    }

    /**
     * 获取当前用户的id
     *
     * @return
     */
    protected abstract String getAccountId();


    @SneakyThrows
    private String reader(ServletInputStream inputStream) {

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
        if (jsonObject == null) {
            return null;
        }

        return jsonObject.toJSONString();
    }
}