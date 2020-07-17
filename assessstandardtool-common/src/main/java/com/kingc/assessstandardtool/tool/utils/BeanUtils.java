package com.kingc.assessstandardtool.tool.utils;


import com.kingc.assessstandardtool.tool.exception.ConverterException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 转换工具类
 */
public class BeanUtils {


    /**
     * 转换实体对象根据映射规则.
     *
     * @param sources     资源
     * @param target      目标对象
     * @param ruleMapping 映射规则
     * @param <T>
     * @param <D>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T extends Object, D extends Class> Object execution(T sources, D target, Map<String, String> ruleMapping) {

        if (ruleMapping == null || ruleMapping.isEmpty()) {
            throw new ConverterException("映射规则不能为空");
        }

        try {
            Field[] sourcesFields = getAllFields(sources);
            Object targetInstances = target.newInstance();
            if (sourcesFields.length == 0) {
                return null;
            }

            for (Field field : sourcesFields) {

                /**忽略serialVersionUID**/
                if ("serialVersionUID".equals(field.getName())) {
                    continue;
                }

                Object val = FieldUtils.readField(sources, field.getName(), true);
                if (val != null) {
                    FieldUtils.writeDeclaredField(targetInstances, ruleMapping.get(field.getName()), val, true);
                }
            }
            return targetInstances;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * entity-->>dto 转换
     *
     * @param dtoClz
     * @param data
     * @return
     */
    public static <D, Clz extends Class, Entity extends Serializable> Entity copyProperties(D data, Clz dtoClz) {
        Object o = org.springframework.beans.BeanUtils.instantiateClass(dtoClz);
        org.springframework.beans.BeanUtils.copyProperties(data, o);
        return (Entity) o;
    }

    /**
     * 将数据的entity属性复制到新的数组中，新的数组由指定的class类型的实例组成
     *
     * @param data
     * @param dtoClz
     * @return
     */
    public static <T extends Class, D extends List> List copyArray(D data, T dtoClz) {

        if (data == null || CollectionUtils.isEmpty(data)) {
            return null;
        }
        return copy(dtoClz, data);
    }


    private static List copy(Class dtoClz, List data) {
        if (CollectionUtils.isEmpty(data)) {
            return data;
        }
        LinkedList linkedList = new LinkedList<>();

        data.forEach(object -> {
            Object dto = org.springframework.beans.BeanUtils.instantiateClass(dtoClz);
            org.springframework.beans.BeanUtils.copyProperties(object, dto);
            linkedList.add(dto);

        });
        return linkedList;
    }


    /**
     * 获取所有字段，包含父类.
     *
     * @param object
     * @return
     */
    public static Field[] getAllFields(Object object) {
        Class clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }
}
