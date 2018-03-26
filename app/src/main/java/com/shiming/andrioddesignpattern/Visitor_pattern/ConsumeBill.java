package com.shiming.andrioddesignpattern.Visitor_pattern;

/**
 * author： Created by shiming on 2018/3/26 19:18
 * mailbox：lamshiming@sina.com
 */
//消费的单子
public class ConsumeBill implements Bill {
    @Override
    public void accept(AccountBookVisitor Visitor) {
        Visitor.view(this);
    }
    private double amount;

    private String item;

    public ConsumeBill(double amount, String item) {
        super();
        this.amount = amount;
        this.item = item;
    }


    public double getAmount() {
        return amount;
    }

    public String getItem() {
        return item;
    }
}
