package com.shiming.andrioddesignpattern.state_model;

/**
 * Created by shiming on 2017/10/15.
 */

public class CarState implements State{

    private static String mCarState="汽车状态";
    @Override
    public void fight() {
        System.out.println("在路上跑");
    }

    public static String getCarState() {
        return mCarState;
    }
}
