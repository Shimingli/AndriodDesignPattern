package com.shiming.andrioddesignpattern.state_model;

/**
 * author： Created by shiming on 2018/3/23 15:43
 * mailbox：lamshiming@sina.com
 */
//
public class NewContext {
    //定义出所有的状态
    public final static OpenningState OpenningState=new OpenningState();
    public final static ClosingState ClosingState=new ClosingState();
    public final static RunningState RunningState=new RunningState();
    public final static StoppinggState StoppinggState=new StoppinggState();

    private LiftState mLiftState;

    public LiftState getLiftState() {
        return mLiftState;
    }

    public void setLiftState(LiftState liftState) {
        mLiftState = liftState;
        //把当前的环境通知到各个实现类中
        liftState.setNewContext(this);
    }
    public void open(){
        this.mLiftState.open();
    }
    public void close(){
        this.mLiftState.close();
    }
    public void run(){
        this.mLiftState.run();
    }
    public void stop(){
        this.mLiftState.stop();
    }
}
