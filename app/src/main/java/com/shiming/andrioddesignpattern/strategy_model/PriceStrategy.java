package com.shiming.andrioddesignpattern.strategy_model;

/**
 * Created by shiming on 2017/9/21.
 * strategy strategy 策略  价格策略的接口
 *
 */

public interface PriceStrategy {
    int setPrice(int orgPrice);
}
