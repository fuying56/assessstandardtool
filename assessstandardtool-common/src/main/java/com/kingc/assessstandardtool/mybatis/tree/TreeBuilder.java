package com.kingc.assessstandardtool.mybatis.tree;


import com.kingc.assessstandardtool.tool.utils.UtilsString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 转换器，将继承TreeNode接口的list集合转换为树结构
 * @author Administrator
 */
@SuppressWarnings("unchecked")
public class TreeBuilder {

    /**
     * 根据当前节点逆推树
     *
     * @param currentNode 当前节点
     * @param allNode     所有节点
     * @return
     */
    public static <T extends TreeNode> List reverseNode(TreeNode currentNode, List<T> allNode, Set<T> list) {

        if (list == null) {
            list = new HashSet<T>();
        }

        for (T node : allNode) {
            if (UtilsString.equalsIgnoreCase(currentNode.getPid(), node.getId())) {
                list.add(node);

                if (UtilsString.isNotBlank(node.getPid())) {
                    reverseNode(node, allNode, list);
                }
            }
        }

        return new ArrayList(list);
    }


    /**
     * 使用递归方法建树
     *
     * @param treeNodes 接口规范.
     * @return
     */
    public static <T extends TreeNode> List buildByRecursive(List<T> treeNodes) {
        List trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (UtilsString.equalsIgnoreCase(TreeNode.getTopId(), treeNode.getPid())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public static <T extends TreeNode> TreeNode findChildren(TreeNode treeNode, List<T> treeNodes) {
        for (TreeNode it : treeNodes) {
            if (treeNode.getId().equals(it.getPid())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

}
