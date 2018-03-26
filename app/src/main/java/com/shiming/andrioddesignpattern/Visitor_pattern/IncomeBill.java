package com.shiming.andrioddesignpattern.Visitor_pattern;

/**
 * author： Created by shiming on 2018/3/26 19:20
 * mailbox：lamshiming@sina.com
 */

//收入单子
public class IncomeBill implements Bill{

    private double amount;

    private String item;

    public IncomeBill(double amount, String item) {
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

    @Override
    public void accept(AccountBookVisitor Visitor) {
        Visitor.view(this);
    }
}