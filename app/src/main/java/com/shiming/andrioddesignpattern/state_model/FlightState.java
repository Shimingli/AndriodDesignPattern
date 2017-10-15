package com.shiming.andrioddesignpattern.state_model;

/**
 * Created by shiming on 2017/10/15.
 */

public class FlightState implements State {
    private static String mFlightSate="飞行状态";
    @Override
    public void fight() {
        System.out.println("在天上飞");
    }

    public static String getmFlightSate() {
        return mFlightSate;
    }
}
