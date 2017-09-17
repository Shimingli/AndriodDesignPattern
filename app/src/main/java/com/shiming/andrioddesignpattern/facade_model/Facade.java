package com.shiming.andrioddesignpattern.facade_model;

/**
 * Created by shiming on 2017/9/17.
 * 外观类拿起手机接电话，放下手机的挂掉电话
 * 外观模式很好理解，但是，在项目中使用起来需要格外注意
 * 对于逻辑任务经常改变的情况，不要轻易的使用这种模式，
 */

public class Facade {
    HandImpl mHand=new HandImpl();
    Phone mPhone=new PhoneImpl();
    public void doFirst(){
        mPhone.dail();
        mHand.takePhone();
    }
    public void close(){
        mPhone.hangUp();
        mHand.closePhone();
    }
}
