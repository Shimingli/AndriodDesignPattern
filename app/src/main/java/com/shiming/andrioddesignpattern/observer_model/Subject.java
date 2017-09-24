package com.shiming.andrioddesignpattern.observer_model;

/**
 * Created by shiming on 2017/9/24.
 * 抽象被观察者Subject
 */

public interface Subject {
    /**
     * 增加订阅者
     * @param observer
     */
    void  attach(Observer observer);

    /**
     * 删除订阅者
     * @param observer
     */
    void detach(Observer observer);

    /**
     * 通知订阅者更新消息
     * @param message
     */
    void notifyMessage(String message);

}
