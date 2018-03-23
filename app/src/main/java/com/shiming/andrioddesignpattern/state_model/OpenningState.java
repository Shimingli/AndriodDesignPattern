package com.shiming.andrioddesignpattern.state_model;

/**
 * author： Created by shiming on 2018/3/23 16:02
 * mailbox：lamshiming@sina.com
 */

public class OpenningState extends LiftState {
    @Override
    public void open() {
        System.out.println("电梯门开了...");
    }

    @Override
    public void close() {
        mNewContext.setLiftState(NewContext.ClosingState);
        mNewContext.getLiftState().close();
    }

    @Override
    public void run() {
       //开着门，就想跑是不行的
    }

    @Override
    public void stop() {
      //已经停止了的哦
    }
}
