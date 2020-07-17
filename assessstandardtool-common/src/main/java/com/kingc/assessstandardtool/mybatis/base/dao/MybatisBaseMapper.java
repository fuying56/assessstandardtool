package com.kingc.assessstandardtool.mybatis.base.dao;


import com.kingc.assessstandardtool.mybatis.mapper.InsertListMapper;
import com.kingc.assessstandardtool.mybatis.mapper.SelectByIdsFilterByDelFlagMapper;
import com.kingc.assessstandardtool.mybatis.mapper.SoftDeleteByIdsMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;

/**
 * @author Administrator
 */
public interface MybatisBaseMapper<T extends Serializable> extends
        Mapper<T>,
        InsertListMapper<T>,
        IdsMapper<T>,
        SoftDeleteByIdsMapper<T>,
        SelectByIdsFilterByDelFlagMapper<T> {

}