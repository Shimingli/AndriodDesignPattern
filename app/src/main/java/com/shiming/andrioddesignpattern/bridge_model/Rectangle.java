package com.shiming.andrioddesignpattern.bridge_model;

/**
 * Created by shiming on 2017/9/18.
 * 长方形实现了形状
 */

public class Rectangle extends Shape {
    @Override
    public String draw() {
       return mDrawObject.drawColor("Rectangle");
    }
}
