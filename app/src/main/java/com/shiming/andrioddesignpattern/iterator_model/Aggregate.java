package com.shiming.andrioddesignpattern.iterator_model;

/**
 * Created by shiming on 2017/9/24.
 * Aggregate 聚集体
 */

public abstract class Aggregate {

    /**
     * 工程的方法，创建相应迭代子对象的接口
     * @return
     */
     abstract Iterator createIterator();
}
