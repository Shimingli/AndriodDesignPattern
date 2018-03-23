package com.shiming.andrioddesignpattern.state_model;

/**
 * author： Created by shiming on 2018/3/23 16:02
 * mailbox：lamshiming@sina.com
 */

public class RunningState extends LiftState {
    @Override
    public void open() {
        //不能开
    }

    @Override
    public void close() {
      //不能光
    }

    @Override
    public void run() {
        System.out.println("电梯运行...");
    }

    @Override
    public void stop() {
       super.mNewContext.setLiftState(NewContext.StoppinggState);
        mNewContext.getLiftState().stop();
    }
}
