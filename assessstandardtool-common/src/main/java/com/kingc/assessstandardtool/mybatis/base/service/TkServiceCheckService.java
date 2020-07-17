package com.kingc.assessstandardtool.mybatis.base.service;



import com.kingc.assessstandardtool.mybatis.base.dao.MybatisBaseMapper;
import com.kingc.assessstandardtool.mybatis.exception.BaseServiceInitException;
import com.kingc.assessstandardtool.tool.utils.UtilsReflections;
import lombok.Getter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * 初始化baseService校验
 *
 * @author Administrator
 */
@SuppressWarnings("ALL")
public class TkServiceCheckService<T extends Serializable, D extends MybatisBaseMapper> {

    @Getter
    private T t;

    @Getter
    private Set<Field> fields;

    public TkServiceCheckService() {

        Class classGenricType = UtilsReflections.getClassGenricType(this.getClass(), 0);
        if (classGenricType == null) {
            throw new BaseServiceInitException("没有找到" + classGenricType.getName());
        }

        if (classGenricType.getAnnotation(Table.class) == null) {
            throw new BaseServiceInitException(classGenricType.getName() + "缺少@Table注解");
        }

        Set<Field> fields = UtilsReflections.getAllFields(classGenricType);
        if (fields == null) {
            return;
        }

        Boolean containIdField = false;
        for (Field field : fields) {
            if (field.getAnnotation(Id.class) != null) {
                containIdField = true;
            }
        }

        if (!containIdField) {
            throw new BaseServiceInitException(classGenricType.getName() + "中缺少@Id注解");
        }

        this.fields = fields;
    }

}
