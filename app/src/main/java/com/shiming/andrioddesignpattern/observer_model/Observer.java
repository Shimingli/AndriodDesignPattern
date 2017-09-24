package com.shiming.andrioddesignpattern.observer_model;

/**
 * Created by shiming on 2017/9/23.
 * observer ：抽象观察者，是观察者的抽象类，它定义了一个更新的接口，使得在得到
 * 主题更改通知时候更新自己
 */

public interface Observer {
    void update(String message);
}
