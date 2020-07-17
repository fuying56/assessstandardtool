package com.kingc.assessstandardtool.tool.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections4.ComparatorUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class UtilsCollection {


    /**
     * 根据某个字段排序.
     *
     * @param collection 集合.
     * @param filedName  排序字段.
     * @param isDesc     是否降序.
     */
    public static void sort(List collection, String filedName, boolean isDesc) {
        Comparator<?> mycmp = ComparableComparator.getInstance();
        mycmp = ComparatorUtils.nullLowComparator(mycmp); // 允许null
        if (isDesc) {
            mycmp = ComparatorUtils.reversedComparator(mycmp); // 逆序
        }
        Collections.sort(collection, new BeanComparator(filedName, mycmp));
    }
}
