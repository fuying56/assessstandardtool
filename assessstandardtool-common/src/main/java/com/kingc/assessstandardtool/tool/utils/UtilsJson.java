package com.kingc.assessstandardtool.tool.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;
import java.util.Map;

public final class UtilsJson {

    public static Map<String, Object> convertJsonToMap(String json) {
        return JSON.parseObject(json, new TypeReference<Map<String, Object>>() {
        });
    }


    public static List convertJsonToList(String json, Class cls) {
        return JSON.parseArray(json, cls);
    }

    public static boolean validateJsonFormat(String str) {
        return UtilsJsonExpand.getUtilsJsonExpand().validate(str);
    }

}