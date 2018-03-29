package com.shiming.andrioddesignpattern.strategy_model;

/**
 * author： Created by shiming on 2018/3/29 16:26
 * mailbox：lamshiming@sina.com
 */
@PriceRegion(min = 20000)
public class GoldVip implements INewPriceStrategy {
    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice*0.7;
    }
}
