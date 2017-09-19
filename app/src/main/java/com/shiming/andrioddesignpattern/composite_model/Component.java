package com.shiming.andrioddesignpattern.composite_model;

/**
 * Created by shiming on 2017/9/19.
 * 一个抽象的构建，声明一个接口访问和管理Component的子部件
 */

public abstract class Component {

    public final String mName;

    public Component(String name) {
        mName = name;
    }
    //增加一个节点
    public abstract void add(Component component);
    //删除一个节点
    public abstract void remove(Component component);
    //显示层级结构
    public abstract void disPlay(int level);
}
