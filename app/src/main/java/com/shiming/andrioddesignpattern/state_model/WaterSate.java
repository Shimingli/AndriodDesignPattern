package com.shiming.andrioddesignpattern.state_model;

/**
 * Created by shiming on 2017/10/15.
 */

public class WaterSate implements State {
    private static String mWaterState="潜水状态";
    @Override
    public void fight() {
        System.out.println("在水里游");
    }

    public static String getmWaterState() {
        return mWaterState;
    }
}
