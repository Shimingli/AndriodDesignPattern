package com.shiming.andrioddesignpattern.state_model;

/**
 * author： Created by shiming on 2018/3/23 16:02
 * mailbox：lamshiming@sina.com
 */

public class ClosingState extends LiftState {
    @Override
    public void open() {
        super.mNewContext.setLiftState(NewContext.OpenningState);
        mNewContext.getLiftState().open();
    }

    @Override
    public void close() {
        System.out.println("电梯门关了...");
    }

    @Override
    public void run() {
        super.mNewContext.setLiftState(NewContext.RunningState);
        mNewContext.getLiftState().run();
    }

    @Override
    public void stop() {
        super.mNewContext.setLiftState(NewContext.StoppinggState);
        mNewContext.getLiftState().stop();
    }
}
