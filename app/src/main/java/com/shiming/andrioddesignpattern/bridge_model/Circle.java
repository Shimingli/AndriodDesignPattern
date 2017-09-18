package com.shiming.andrioddesignpattern.bridge_model;

/**
 * Created by shiming on 2017/9/18.
 * 继承形状类，实现需要实现那种的形状
 *
 */

public class Circle extends Shape {
    @Override
    public String draw() {
      return mDrawObject.drawColor("Circle");
    }
}
