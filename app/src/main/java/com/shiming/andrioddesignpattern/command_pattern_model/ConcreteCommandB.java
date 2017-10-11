package com.shiming.andrioddesignpattern.command_pattern_model;

/**
 * @author shiming
 * @version v1.0 create at 2017/10/9
 * @des  具体的命名者
 */
public class ConcreteCommandB implements Command {

    private final Receiver mReceiver;

    public ConcreteCommandB(Receiver receiver) {
        mReceiver = receiver;
    }

    /**
     * 为了测试方便，这里返回一个值
     * @return
     */
    @Override
    public String execute() {
       return mReceiver.doWhatTwo();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
