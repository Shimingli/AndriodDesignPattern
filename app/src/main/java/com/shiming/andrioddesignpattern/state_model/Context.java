package com.shiming.andrioddesignpattern.state_model;

/**
 * Created by shiming on 2017/10/15.
 */

public class Context  {
    private String mDes;
    private State mState;
    public State transFormTo(State state){
        mState=state;
        return state;
    }

    public void doWhat(){
        mState.fight();
    }

    public Context(String s,State state){
        mDes=s;
        mState=state;
    }
}
