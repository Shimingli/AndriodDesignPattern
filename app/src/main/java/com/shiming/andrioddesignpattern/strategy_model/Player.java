package com.shiming.andrioddesignpattern.strategy_model;

/**
 * author： Created by shiming on 2018/3/29 16:35
 * mailbox：lamshiming@sina.com
 */

public class Player {
    private Double totalAmount = 0D;//客户在鹅厂消费的总额
    private Double amount = 0D;//客户单次消费金额
    private INewPriceStrategy calPrice = new Orgnic();//每个客户都有一个计算价格的策略，初始都是普通计算，即原价

    //客户购买皮肤，就会增加它的总额
    public void buy(Double amount) {
        this.amount = amount;
        totalAmount += amount;
        /* 变化点，我们将策略的制定转移给了策略工厂，将这部分责任分离出去 */
//        calPrice = CalPriceFactory.getInstance().createCalPrice(this);
    }

    //计算客户最终要付的钱
    public Double calLastAmount() {
        return calPrice.calPrice(amount);
    }

    public Double getTotalAmount() {
        return totalAmount;
    }
}
