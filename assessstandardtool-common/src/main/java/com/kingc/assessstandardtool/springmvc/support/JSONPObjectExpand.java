package com.kingc.assessstandardtool.springmvc.support;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Administrator
 * @date 2017/7/13 0013
 * Fastjson的JSONP消息对象.
 */
@Getter
@Setter
public class JSONPObjectExpand {


    private String function;

    private Object json;

    public JSONPObjectExpand(String function, Object json) {
        this.function = function;
        this.json = json;
    }

}
