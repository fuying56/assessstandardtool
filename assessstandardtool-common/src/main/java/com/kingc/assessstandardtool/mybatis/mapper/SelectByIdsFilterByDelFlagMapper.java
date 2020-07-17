package com.kingc.assessstandardtool.mybatis.mapper;

import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 *
 * @author SunAowei
 * @date 2017/10/28
 */
public interface SelectByIdsFilterByDelFlagMapper<T> {

    @SelectProvider(type = SpecialProvider.class, method = "dynamicSQL")
    List<T> selectByIdsFilterByDelFlag(String ids);
}
