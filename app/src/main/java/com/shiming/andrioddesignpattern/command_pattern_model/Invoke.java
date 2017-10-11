package com.shiming.andrioddesignpattern.command_pattern_model;

/**
 * @author shiming
 * @version v1.0 create at 2017/10/9
 * @des
 */
public class Invoke {

    private Command mCommandA;

    public void setCommandA(Command commandA)
    {
        mCommandA = commandA;
    }
    private Command mCommandB;

    public void setCommandB(Command commandA)
    {
        mCommandB = commandA;
    }
    public String  invoke(int args){
        String execute="";
        if (args==0){
           execute = mCommandA.execute();
        }else if (args==1){
            execute = mCommandB.execute();
        }
        return execute;
    }
}
