package com.shiming.andrioddesignpattern.strategy_model;

/**
 * Created by shiming on 2017/9/21.
 */

public class ContextPrice  {

    private  PriceStrategy mPriceStrategy;

    public ContextPrice(PriceStrategy priceStrategy) {
        mPriceStrategy = priceStrategy;
    }
    public int setNewPrice(int price){
       return mPriceStrategy.setPrice(price);
    }

    public void setNewStrategy(SevenPriceStrategy newStrategy) {
        mPriceStrategy = newStrategy;
    }
}
