package com.kingc.assessstandardtool.business.base.entity.converter;


import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@SuppressWarnings("unchecked")
public class DTOConverter {

    /**
     * entity-->>dto 转换
     *
     * @param dtoClz
     * @param data
     * @return
     */
    public static <T extends Class, D> Object convert(T dtoClz, D data) {

        if (data == null) {
            return null;
        }

        List convertList = convert(dtoClz, data instanceof List ? (List) data : Arrays.asList(data));

        return data instanceof List ? convertList : convertList.get(0);

    }


    private static List convert(Class dtoClz, List data) {
        if (CollectionUtils.isEmpty(data)) {
            return data;
        }
        LinkedList linkedList = new LinkedList<>();

        data.forEach(object -> {
            Object dto = BeanUtils.instantiateClass(dtoClz);
            BeanUtils.copyProperties(object, dto);
            linkedList.add(dto);

        });
        return linkedList;
    }


    /**
     * 获取指定位置的泛型类型.
     *
     * @param object   使用泛型的对象.
     * @param position 需要获取的泛型位置.
     * @return 返回泛型的class
     */
    private static Object getGeneric(Object object, int position) {
        ParameterizedType parameterizedType = (ParameterizedType) object.getClass().getGenericSuperclass();
        Type type = object.getClass().getGenericSuperclass();
        // 判断 是否泛型
        if (type instanceof ParameterizedType) {
            // 返回表示此类型实际类型参数的Type对象的数组.
            //将第一个泛型T对应的类返回（这里只有一个）
            return (Class) parameterizedType.getActualTypeArguments()[position];
        } else {
            //若没有给定泛型，则返回Object类
            return object.getClass();
        }
    }


}
