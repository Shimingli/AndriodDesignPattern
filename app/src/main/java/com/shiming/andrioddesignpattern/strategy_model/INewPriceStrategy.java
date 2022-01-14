package com.shiming.andrioddesignpattern.strategy_model;

/**
 * author： Created by shiming on 2018/3/29 16:22
 * mailbox：lamshiming@sina.com
 */
// 新的 策略的接口
public interface INewPriceStrategy {
    Double calPrice(Double orgnicPrice);
}
