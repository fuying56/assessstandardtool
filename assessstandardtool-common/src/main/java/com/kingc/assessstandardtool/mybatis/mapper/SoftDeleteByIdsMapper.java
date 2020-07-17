package com.kingc.assessstandardtool.mybatis.mapper;

import org.apache.ibatis.annotations.DeleteProvider;

/**
 * Created by SunAowei on 2017/10/13.
 */
public interface SoftDeleteByIdsMapper<T> {
    @DeleteProvider(type = SpecialProvider.class, method = "dynamicSQL")
    int softDeleteByIds(String ids);

}
