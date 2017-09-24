package com.shiming.andrioddesignpattern.observer_model;

/**
 * Created by shiming on 2017/9/23.
 * 具体观察者，是实现抽象观察者定义的更新接口，以便在得到更改通知时更新自身的状态
 */

public class ConcreteObserver implements Observer {

    private String mMessage;
    private final String mName;

    public ConcreteObserver(String name) {
        mName = name;
    }

    @Override
    public void update(String message) {
        mMessage = message;
    }
    public String getMessage(){
        return mName+"收到了："+mMessage;
    }
}
