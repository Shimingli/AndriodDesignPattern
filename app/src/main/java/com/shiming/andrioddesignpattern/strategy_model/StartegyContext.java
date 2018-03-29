package com.shiming.andrioddesignpattern.strategy_model;

/**
 * author： Created by shiming on 2018/3/29 17:22
 * mailbox：lamshiming@sina.com
 */

public class StartegyContext {
    //持有一个具体策略的对象
    private INewPriceStrategy strategy;
    /**
     * 构造函数，传入一个具体策略对象
     * @param strategy    具体策略对象
     */
    public StartegyContext(INewPriceStrategy strategy){
        this.strategy = strategy;
    }
    /**
     * 策略方法
     */
    public void contextInterface(){

        strategy.calPrice(10000D);
    }
}
