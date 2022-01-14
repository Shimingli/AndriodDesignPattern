package com.shiming.andrioddesignpattern.strategy_model;

/**
 * Created by shiming on 2017/9/21.
 */

public class FivePriceStrategy implements PriceStrategy {
    @Override
    public int setPrice(int orgPrice) {
        return (int) (orgPrice * 0.5);
    }
}
