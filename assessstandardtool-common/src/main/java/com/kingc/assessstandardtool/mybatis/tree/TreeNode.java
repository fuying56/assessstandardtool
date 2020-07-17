package com.kingc.assessstandardtool.mybatis.tree;

import java.util.List;

/**
 * 转换树节点.
 *
 * @param <T>
 * @author Administrator
 */
public interface TreeNode<T> {

    /**
     * 顶级节点id
     *
     * @return
     */
    static String getTopId() {
        return "0";
    }

    /**
     * 获取id
     *
     * @return
     */
    String getId();

    /**
     * 获取pid
     *
     * @return
     */
    String getPid();

    /**
     * 获取子集
     *
     * @return
     */
    List getChildren();

    /**
     * 设置子集
     *
     * @param children
     */
    void setChildren(List<T> children);
}
