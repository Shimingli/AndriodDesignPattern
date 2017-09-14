package com.shiming.andrioddesignpattern.adapter_model;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/14
 * @des
 */
public class Adapter implements Target {

    private final Adaptee mAdaptee;

    public Adapter(Adaptee adaptee) {
        mAdaptee = adaptee;
    }

    @Override
    public String request() {
        return mAdaptee.getDoWhat();
    }
}
