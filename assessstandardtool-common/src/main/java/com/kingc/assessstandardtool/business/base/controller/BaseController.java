package com.kingc.assessstandardtool.business.base.controller;


import com.kingc.assessstandardtool.business.base.service.BaseService;
import com.kingc.assessstandardtool.business.entity.BaseEntity;
import com.kingc.assessstandardtool.springmvc.model.ResponseContent;

/**
 * 基础控制器接口
 * @author Administrator
 */
public interface BaseController<T extends BaseEntity, D extends BaseService> {

    /**
     * 删除一条记录
     *
     * @param ids 不能为null，则跳转到错误页面
     * @return ModelAndView
     */
    ResponseContent delete(String ids);

    /**
     * 添加一条实体，实体不能为null
     *
     * @param entity 要添加的实体
     */
    ResponseContent save(T entity);

    /**
     * 根据ID查询一个对象
     *
     * @return ResponseContent
     */
    ResponseContent get(String id);

    /**
     * 更新一个实体，更新实体不为null的字段,
     * entity的id不能为空.
     *
     * @param entity 要更新的实体
     */
    ResponseContent update(T entity);

    /**
     * 单表分页查询
     *
     * @param t 实体对象
     * @param pageNum 当前页码
     * @param pageSize 分页大小
     * @return 返回分页对象.
     */
    ResponseContent queryPage(T t, int pageNum, int pageSize);


    /**
     * 查询所有数据
     *
     * @return 返回分页对象.
     */
    ResponseContent selectAll();
}
