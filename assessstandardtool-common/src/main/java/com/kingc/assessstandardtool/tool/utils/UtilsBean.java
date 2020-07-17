package com.kingc.assessstandardtool.tool.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Map;

/**
 *
 */
public final class UtilsBean {

    /**
     * map转obj
     *
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (null == map) {
            return null;
        }
        Object obj = beanClass.newInstance();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            UtilsReflections.invokeSetter(obj, entry.getKey(), entry.getValue());
        }
        return obj;
    }

    /**
     * map转obj，可以设置忽略字段
     * @param map
     * @param beanClass
     * @param excludedFields
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass, String[] excludedFields) throws Exception {
        if (null == map) {
            return null;
        }
        Object obj = beanClass.newInstance();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (ArrayUtils.isNotEmpty(excludedFields) && ArrayUtils.contains(excludedFields,entry.getKey())) {
                continue;
            }
            UtilsReflections.invokeSetter(obj, entry.getKey(), entry.getValue());
        }
        return obj;
    }

    /**
     * @param object
     * @param keySet
     * @return
     */
    public static Map<String, Object> removeAttribute(Object object, String... keySet) {

        Map<String, Object> map = objectToMap(object);

        for (String key : keySet) {
            if (map.containsKey(key)) {
                map.remove(key);
            }
        }

        return map;
    }

    /**
     * object转json.
     *
     * @param object
     * @return
     */
    public static Map<String, Object> objectToMap(Object object) {
        String jsonString = JSON.toJSONString(object);
        return UtilsJson.convertJsonToMap(jsonString);
    }
}
