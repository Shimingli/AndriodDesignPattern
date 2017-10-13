package com.shiming.andrioddesignpattern.memento_model;

/**
 * @author shiming
 * @version v1.0 create at 2017/10/13
 * @des memento 备忘录角色类，备忘录对象将发起人对象传入的状态储存起来
 */
public class Memento {
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;

    public Memento(String state) {
        this.state = state;
    }

}
