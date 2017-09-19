package com.shiming.andrioddesignpattern.bridge_model;

/**
 * Created by shiming on 2017/9/18.
 * 形状抽象类和颜色接口产生关系
 */

public abstract class Shape {

     DrawWhichColor mDrawObject;

    public void setShapColor(DrawWhichColor color){
        mDrawObject = color;
    }

    public abstract String draw();
}
