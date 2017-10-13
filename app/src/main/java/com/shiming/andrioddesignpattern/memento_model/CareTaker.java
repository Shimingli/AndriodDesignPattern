package com.shiming.andrioddesignpattern.memento_model;

/**
 * @author shiming
 * @version v1.0 create at 2017/10/13
 * @des caretaker 负责人角色类，负责人角色负责保存备忘录对象，但是从不修改备忘录对象的内容
 */
public class CareTaker {

    private Memento mMemento;

    /**
     * 取值
     * @return
     */
    public Memento getMemento(){
        return mMemento;
    }

    /**
     * 设置值
     * @param memento
     */
    public void setMemento(Memento memento){
        mMemento=memento;
    }


}
