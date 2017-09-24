package com.shiming.andrioddesignpattern.iterator_model;

/**
 * Created by shiming on 2017/9/24.
 * iterator 迭代器
 */

public interface Iterator {
    /**
     * 移动到第一个元素
     */
    void first();

    /**
     * 迭代方法，移动到下一个元素
     */
    void next();

    /**
     * 是否为最后的一个元素
     */
    boolean isDone();

    /**
     * 返回当前的元素
     * @return
     */
    Object currentItem();




}
