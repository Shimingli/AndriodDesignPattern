package com.shiming.andrioddesignpattern.memento_model;

/**
 * @author shiming
 * @version v1.0 create at 2017/10/13
 * @des originator 发起人角色类，发起人角色利用一个新创建的备忘录对象将自己的内部状态储存起来
 */
public class Originator {
    //状态
    private String state;

    public Memento createMemento(){
        return new Memento(state);
    }

    /**
     * 将发起人恢复到备忘录对象所记载的状态
     * @param memento
     */
    public void recoverMemento(Memento memento){
        state=memento.getState();
    }

    /**
     * 得到保存的状态
     * @return
     */
    public String getState(){
        return state;
    }
     public  void  setState(String info){
         state=info;
     }

}
