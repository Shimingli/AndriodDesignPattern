package com.shiming.andrioddesignpattern.command_pattern_model;

/**
 * @author shiming
 * @version v1.0 create at 2017/10/9
 * @des
 */
public class ConcreteCommandA implements Command {

    private final Receiver mReceiver;

    public ConcreteCommandA(Receiver receiver) {
        mReceiver = receiver;
    }

    /**
     * 为了测试方便，这里返回一个值
     * @return
     */
    @Override
    public String execute() {
       return mReceiver.doWhatFrist();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
