package com.shiming.andrioddesignpattern.Visitor_pattern;

/**
 * author： Created by shiming on 2018/3/26 19:16
 * mailbox：lamshiming@sina.com
 */
//Visitor  老板只关心收入和支出的总额，而注会只关注该交税的是否交税
public interface AccountBookVisitor {
    void  view(ConsumeBill consumeBill);
    void  view(IncomeBill incomeBill);
}
