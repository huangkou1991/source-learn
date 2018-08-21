package com.learn.java8.fork;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @Author :lwy
 * @Date : 2018/8/21 17:52
 * @Description :
 */
public class TreeNode {

    int value;

    Set<TreeNode> children;

    public TreeNode(int value, TreeNode... children) {
        this.value = value;
        this.children = Sets.newHashSet(children);
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Set<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(Set<TreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreedNode{" +
                "value=" + value +
                ", children=" + children +
                '}';
    }
}
