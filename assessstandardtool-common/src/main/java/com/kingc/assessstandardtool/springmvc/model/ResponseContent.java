package com.kingc.assessstandardtool.springmvc.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class ResponseContent extends HashMap<String, Object> {

    private static final long serialVersionUID = 311834895183631187L;

    /**
     * 成功
     */
    private static final String SUCCESS_KEY = "success";

    private static final String MESSAGE = "message";

    /**
     * 处理成功
     *
     * @return 处理成功对象
     */
    public static ResponseContent success() {
        ResponseContent entity = new ResponseContent();
        entity.put(SUCCESS_KEY, true);
        return entity;
    }

    /**
     * 处理成功
     *
     * @return 处理失败对象
     */
    public static ResponseContent success(String msg) {
        ResponseContent entity = new ResponseContent();
        entity.setMessage(msg);
        entity.put(SUCCESS_KEY, true);
        return entity;
    }

    /**
     * 处理失败
     *
     * @return 返回处理失败对象
     */
    public static ResponseContent fail() {
        ResponseContent entity = new ResponseContent();
        entity.put(SUCCESS_KEY, false);
        return entity;
    }

    /**
     * 处理失败
     *
     * @return 返回处理失败对象
     */
    public static ResponseContent fail(String msg) {
        ResponseContent entity = new ResponseContent();
        entity.setMessage(msg);
        entity.put(SUCCESS_KEY, false);
        return entity;
    }

    /**
     * 设置响应对象消息
     *
     * @param msg
     * @return
     */
    public ResponseContent setMessage(Object msg) {
        this.put(MESSAGE, msg);
        return this;
    }

    /**
     * 为响应对象增加单个参数
     *
     * @param key
     * @param value
     * @return
     */
    public ResponseContent addParam(String key, Object value) {
        this.put(key, value);
        return this;
    }

    /**
     * 为响应对象增加多个参数
     *
     * @param params map 集合
     * @return
     */
    public ResponseContent addAllParams(Map<String, Object> params) {
        this.putAll(params);
        return this;
    }
}
