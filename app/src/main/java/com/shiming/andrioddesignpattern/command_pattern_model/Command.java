package com.shiming.andrioddesignpattern.command_pattern_model;

/**
 * @author shiming
 * @version v1.0 create at 2017/10/9
 * @des 命令的角色
 */
public interface Command {
    //执行
    String execute();
    //不用做
    void undo();
    //重新的做
    void redo();
}
