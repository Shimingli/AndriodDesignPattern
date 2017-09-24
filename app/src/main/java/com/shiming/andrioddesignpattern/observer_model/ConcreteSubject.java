package com.shiming.andrioddesignpattern.observer_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiming on 2017/9/24.
 * 具体观察者，改角色将有关状态存入具体观察者，在具体主题内部状态发生改变时，给所有注册过的
 * 观察者发送通知
 */

public class ConcreteSubject implements Subject {
    private List<Observer> mOberver= new ArrayList<Observer>();

    @Override
    public void attach(Observer observer) {
        mOberver.add(observer);
    }

    @Override
    public void detach(Observer observer) {
       mOberver.remove(observer);
    }

    @Override
    public void notifyMessage(String message) {
        for (Observer bean :mOberver) {
            bean.update(message);
        }
    }
}
