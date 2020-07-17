package com.kingc.assessstandardtool.springmvc.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;


/**
 * 返回数据转换器.
 */
public class FastJsonJsonConverter extends FastJsonHttpMessageConverter {

    private static final Logger logger = LoggerFactory.getLogger(FastJsonJsonConverter.class);

    /**
     * 不支持接口类型的转换
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        if (clazz.isInterface()) {
            logger.warn("{} 不支持接口类型的方法参数转换，请使用实体类。", this.getClass().getName());
            return false;
        }
        return super.supports(clazz);
    }

    /**
     * 支持jsonp.
     */
    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (obj instanceof JSONPObjectExpand) {
            JSONPObjectExpand jsonp = (JSONPObjectExpand) obj;
            OutputStream out = outputMessage.getBody();
            String text = jsonp.getFunction() + "(" + JSON.toJSONString(jsonp.getJson(), getFeatures()) + ")";
            byte[] bytes = text.getBytes(getCharset());
            out.write(bytes);
        } else {
            super.writeInternal(obj, outputMessage);
        }
    }


}
