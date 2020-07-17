package com.kingc.assessstandardtool.mybatis.base.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.kingc.assessstandardtool.mybatis.base.dao.MybatisBaseMapper;
import com.kingc.assessstandardtool.mybatis.base.service.AbstractBaseService;
import com.kingc.assessstandardtool.mybatis.base.service.TkServiceCheckService;
import com.kingc.assessstandardtool.mybatis.base.service.annotation.CreateBy;
import com.kingc.assessstandardtool.mybatis.base.service.annotation.CreateDate;
import com.kingc.assessstandardtool.mybatis.base.service.annotation.UpdateBy;
import com.kingc.assessstandardtool.mybatis.base.service.annotation.UpdateDate;
import com.kingc.assessstandardtool.tool.utils.UtilsReflections;
import com.kingc.assessstandardtool.tool.utils.UtilsString;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 通用业务封装
 *
 * @author Administrator
 */
@SuppressWarnings("ALL")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public abstract class AbstractBaseServiceImpl<T extends Serializable, D extends MybatisBaseMapper> extends TkServiceCheckService<T, D> implements AbstractBaseService<T> {

    @Autowired
    protected D d;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(T record) {
        preSaveHandler(record);
        d.insertSelective(record);
    }

    @Override
    public List<T> select(T t) {
        return d.select(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByPrimaryKeySelective(T record) {

        preUpdateHandler(record);

        d.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPrimaryKey(String id) {
        d.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByExample(Example example) {
        d.deleteByExample(example);
    }

    @Override
    public T selectOne(T record) {
        return (T) d.selectOne(record);
    }

    @Override
    public List<T> selectByExample(Example example) {
        return d.selectByExample(example);
    }

    @Override
    public List selectAll() {
        return d.selectAll();
    }

    @Override
    public int selectCount(T record) {
        return d.selectCount(record);
    }

    @Override
    public int selectCountByExample(Example example) {
        return d.selectCountByExample(example);
    }

    @Override
    public T selectByPrimaryKey(String id) {
        return (T) d.selectByPrimaryKey(id);
    }


    @Override
    public PageInfo<T> queryExampleByPage(Example example, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<T> queryByPage(T entity, int pageNum, int pageSize) {

        Example example = new Example(entity.getClass());
        Example.Criteria criteria = example.createCriteria();

//        根据对象的值进行like查询，并且跳过被Transient修饰的字段
        getFiledParam:
        for (Field field : super.getFields()) {

            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Transient) {
                    continue getFiledParam;
                }
            }
            Object fieldValue = UtilsReflections.getFieldValue(entity, field.getName());

            if (fieldValue != null && UtilsString.isNotBlank(fieldValue.toString())) {
                criteria.andLike(field.getName(), "%" + UtilsReflections.getFieldValue(entity, field.getName()) + "%");
            }
        }

        return queryExampleByPage(example, pageNum, pageSize);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsert(List<T> arr) {

        if (CollectionUtils.isEmpty(arr)) {
            return;
        }

        for (T t : arr) {
            save(t);
        }
    }

    /**
     * 保存之前的预处理操作
     */
    protected void preSaveHandler(T entity) {

        for (Field field : super.getFields()) {
            if (field.getAnnotation(Id.class) != null) {
                setFieldValue(entity, field, Id.class, UtilsString.getUUID());
            }

            if (field.getAnnotation(CreateBy.class) != null) {
                setFieldValue(entity, field, CreateBy.class, getCurrentUserId());
            }

            if (field.getAnnotation(CreateDate.class) != null) {
                setFieldValue(entity, field, CreateDate.class, new Date());
            }
        }

        preUpdateHandler(entity);
    }

    /**
     * 修改之前的预处理操作
     */
    protected void preUpdateHandler(T entity) {
        for (Field field : super.getFields()) {
            if (field.getAnnotation(UpdateBy.class) != null) {
                setFieldValue(entity, field, UpdateBy.class, getCurrentUserId());
            }
            if (field.getAnnotation(UpdateDate.class) != null) {
                setFieldValue(entity, field, UpdateDate.class, new Date());
            }
        }
    }

    private <Anno extends Class> void setFieldValue(T t, Field field, Anno clz, Object val) {
        Annotation annotation = field.getAnnotation(clz);
        if (annotation != null) {
            UtilsReflections.setFieldValue(t, field.getName(), val);
        }
    }
}
