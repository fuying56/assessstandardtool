package com.kingc.assessstandardtool.mybatis.mapper;

import org.apache.ibatis.annotations.InsertProvider;

import java.util.List;

public interface InsertListMapper<T> {
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);
}