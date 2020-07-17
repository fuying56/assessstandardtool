package com.kingc.assessstandardtool.mybatis.base.service;

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 通用业务封装.
 *
 * @param <T>
 * @author Administrator
 */
public interface AbstractBaseService<T> {

    /**
     * 插入
     *
     * @param t
     */
    void save(T t);

    /**
     * 根据主键修改
     *
     * @param t
     */
    void updateByPrimaryKeySelective(T t);

    /**
     * 根据主键删除
     *
     * @param id
     */
    void deleteByPrimaryKey(String id);

    /**
     * 根据条件删除
     *
     * @param example
     */
    void deleteByExample(Example example);

    /**
     * 查询单个的数据
     *
     * @param t
     * @return
     */
    T selectOne(T t);

    /**
     * 根据条件查询
     *
     * @param example
     * @return
     */
    List<T> selectByExample(Example example);

    /**
     * 查询所有的数据
     *
     * @return
     */
    List selectAll();

    /**
     * 查询指定字段的数据
     *
     * @param t
     * @return
     */
    List<T> select(T t);

    /**
     * 查询包含对象属性的条数
     *
     * @param t
     * @return
     */
    int selectCount(T t);

    /**
     * 查询条数
     *
     * @param example
     * @return
     */
    int selectCountByExample(Example example);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(String id);

    /**
     * 根据对象属性like查询
     *
     * @param t
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<T> queryByPage(T t, int pageNum, int pageSize);

    /**
     * 带分页查询
     *
     * @param example
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<T> queryExampleByPage(Example example, int pageNum, int pageSize);

    /**
     * 批量保存
     *
     * @param arr
     */
    void batchInsert(List<T> arr);

    /**
     * 获取当前用户的id.
     *
     * @return
     */
    default String getCurrentUserId() {
        return "";
    }
}
