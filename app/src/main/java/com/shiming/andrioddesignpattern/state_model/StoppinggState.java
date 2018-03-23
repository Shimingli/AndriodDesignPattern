package com.shiming.andrioddesignpattern.state_model;

/**
 * author： Created by shiming on 2018/3/23 16:02
 * mailbox：lamshiming@sina.com
 */

public class StoppinggState extends LiftState {
    @Override
    public void open() {
        mNewContext.setLiftState(NewContext.OpenningState);
        mNewContext.getLiftState().open();
    }

    @Override
    public void close() {
        //电梯停止了，门肯定是关着的呢
    }

    @Override
    public void run() {
        mNewContext.setLiftState(NewContext.RunningState);
        mNewContext.getLiftState().run();
    }

    @Override
    public void stop() {
        System.out.println("电梯停止了...");
    }
}
