package com.shiming.andrioddesignpattern.Visitor_pattern;

/**
 * author： Created by shiming on 2018/3/26 19:24
 * mailbox：lamshiming@sina.com
 */
////注册会计师类，查看账本的类之一
public class CPA implements AccountBookVisitor {
    @Override
    public void view(ConsumeBill consumeBill) {
        if (consumeBill.getItem().equals("工资")) {
            System.out.println("注会查看工资是否交个人所得税。");
        }
    }

    @Override
    public void view(IncomeBill incomeBill) {
        System.out.println("注会查看收入交税了没。");
    }

}
