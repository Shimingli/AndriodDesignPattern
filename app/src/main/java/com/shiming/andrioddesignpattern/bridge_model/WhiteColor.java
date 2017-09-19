package com.shiming.andrioddesignpattern.bridge_model;

/**
 * Created by shiming on 2017/9/18.
 * 需要那种的颜色
 */

public class WhiteColor implements DrawWhichColor {
    @Override
    public String drawColor(String shape) {

        return "WhiteColor "+shape;
    }
}
