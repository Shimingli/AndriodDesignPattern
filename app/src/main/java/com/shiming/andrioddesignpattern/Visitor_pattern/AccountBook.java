package com.shiming.andrioddesignpattern.Visitor_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * author： Created by shiming on 2018/3/26 19:27
 * mailbox：lamshiming@sina.com
 */
//接下来是账本类，它是当前访问者模式例子中的对象结构
public class AccountBook {
    //单子列表
    private List<Bill> billList = new ArrayList<Bill>();
    //添加单子
    public void addBill(Bill bill){
        billList.add(bill);
    }
    //供账本的查看者查看账本
    public void show(AccountBookVisitor viewer){
        for (Bill bill : billList) {
            bill.accept(viewer);
        }
    }
}
