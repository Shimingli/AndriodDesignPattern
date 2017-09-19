package com.shiming.andrioddesignpattern.bridge_model;

/**
 * Created by shiming on 2017/9/18.
 * 颜色的接口需要关心需要绘制那种的形状
 */

public interface DrawWhichColor {
    String drawColor(String shape);
}
