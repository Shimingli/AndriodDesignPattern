package com.shiming.andrioddesignpattern.state_model;

/**
 * author： Created by shiming on 2018/3/23 15:50
 * mailbox：lamshiming@sina.com
 */
//基类 所有的基类
public abstract class LiftState {

    protected NewContext mNewContext;

    public void setNewContext(NewContext newContext) {
        mNewContext = newContext;
    }
    public abstract void open();
    public abstract void close();
    public abstract void run();
    public abstract void stop();
}
