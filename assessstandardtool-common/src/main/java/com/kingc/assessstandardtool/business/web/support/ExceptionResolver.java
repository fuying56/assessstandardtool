package com.kingc.assessstandardtool.business.web.support;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;

import com.kingc.assessstandardtool.springmvc.exception.BizException;
import com.kingc.assessstandardtool.springmvc.model.ResponseContent;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

@Component
public class ExceptionResolver implements HandlerExceptionResolver {

    private Logger log = LoggerFactory.getLogger(getClass());

    private Map<Class, String> exceptionMsgMap = ImmutableMap.of(
            SQLException.class, "操作数据库异常",
            NullPointerException.class, "空指针异常",
            ClassNotFoundException.class, "程序内部错误,操作失败",
            DataAccessException.class, "数据库操作失败",
            UnauthorizedException.class, "您没有足够的权限");

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {

        ResponseContent responseContent = ResponseContent.fail();

        String message = new ResponseMessageManger().buildResponseMessage(exception);
        responseContent.addParam("message", message);


        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);


        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(responseContent));
            writer.close();
        } catch (IOException ex) {
            log.error("自定义异常出错");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        return null;
    }


    /**
     * 响应消息返回管理
     */
    private class ResponseMessageManger {

        /**
         * 构建响应消息体结构.
         *
         * @param ex 异常.
         * @return
         */
        String buildResponseMessage(Exception ex) {

            String message = "程序内部错误";
            if (null != ex) {
                message += "<br/>" + ex.getMessage();
                StackTraceElement[] stackTraceElements = ex.getStackTrace();
                if (stackTraceElements != null) {
                    message += "<br/>" + stackTraceElements[0];
                    ex.printStackTrace();
                }
            }
            if (ex != null && exceptionMsgMap.containsKey(ex.getClass())) {
                message = exceptionMsgMap.get(ex.getClass());
            } else {

                Throwable rootCause = ExceptionUtils.getRootCause(ex);
                if (ex instanceof BizException) {
                    BizException businessException = (BizException) ex;
                    message = businessException.getMessage();
                } else if (null != rootCause && rootCause instanceof BizException) {
                    message = rootCause.getMessage();
                }
            }

            return message;
        }
    }
}
