package com.shiming.andrioddesignpattern.strategy_model;

/**
 * Created by shiming on 2017/9/21.
 */

public class SevenPriceStrategy implements PriceStrategy {
    @Override
    public int setPrice(int orgPrice) {
        //7折的算法实现
        return (int) (orgPrice*0.7);
    }
}
