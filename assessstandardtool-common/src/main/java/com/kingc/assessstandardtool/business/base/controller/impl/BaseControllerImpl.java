package com.kingc.assessstandardtool.business.base.controller.impl;


import com.github.pagehelper.PageInfo;
import com.kingc.assessstandardtool.business.base.controller.BaseController;
import com.kingc.assessstandardtool.business.base.service.BaseService;
import com.kingc.assessstandardtool.business.entity.BaseEntity;
import com.kingc.assessstandardtool.springmvc.model.ResponseContent;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 基础控制器接口实现类
 * @author Administrator
 */
@SuppressWarnings("unchecked")
public abstract class BaseControllerImpl<T extends BaseEntity, D extends BaseService> implements BaseController<T, D> {


    @Autowired
    protected D d;


    @Override
    @ResponseBody
    @RequestMapping(value = "/delete")
    public ResponseContent delete(String ids) {

        String[] split = ids.split(",");
        for (String id : split) {
            d.deleteByPrimaryKey(id.trim());
        }
        return ResponseContent.success();
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseContent save(@RequestBody T entity) {
        d.save(entity);
        return ResponseContent.success();
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/get")
    public ResponseContent get(String id) {
        return ResponseContent.success().addParam("data", d.selectByPrimaryKey(id));
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseContent update(@RequestBody T entity) {

        if (StringUtils.isBlank(entity.getId())) {
            return ResponseContent.fail();
        }

        d.updateByPrimaryKeySelective(entity);
        return ResponseContent.success();
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/queryPage")
    public ResponseContent queryPage(
            @RequestBody T t, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "13") int pageSize) {

        formatQueryParam(t);

        PageInfo<T> pageInfo = d.queryByPage(t, pageNum, pageSize);

        if (!CollectionUtils.isEmpty(pageInfo.getList())) {
            for (T o : pageInfo.getList()) {
                callback(o);
            }
        }

        return ResponseContent.success().addParam("page", pageInfo);
    }


    @Override
    @ResponseBody
    @RequestMapping(value = "/selectAll")
    public ResponseContent selectAll() {
        List list = d.selectAll();
        return ResponseContent.success().addParam("list", list);
    }

    protected void formatQueryParam(T entity) {
    }

    protected void callback(T entity) {
    }
}
