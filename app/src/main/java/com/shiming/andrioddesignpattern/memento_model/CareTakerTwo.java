package com.shiming.andrioddesignpattern.memento_model;

/**
 * @author shiming
 * @version v1.0 create at 2017/10/13
 * @des
 */
public class CareTakerTwo {
    private MementoTwo memoto;
    public void save(MementoTwo memoto){
        this.memoto=memoto;
    }
    public MementoTwo load(){
        return memoto;
    }
}
