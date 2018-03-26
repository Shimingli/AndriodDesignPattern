package com.shiming.andrioddesignpattern.Visitor_pattern;

/**
 * author： Created by shiming on 2018/3/26 19:15
 * mailbox：lamshiming@sina.com
 * 我们都知道财务都是有账本的，这个账本就可以作为一个对象结构，
 * 而它其中的元素有两种，收入和支出，这满足我们访问者模式的要求，即元素的个数是稳定的，
 * 因为账本中的元素只能是收入和支出。
 * 查看账本的人可能有这样几种，比如老板，会计事务所的注会，财务主管，等等。而这些人在看账本的时候显然目的和行为是不同的。
 */
//单个单子的接口， 相当于Element
public interface Bill {
    void accept(AccountBookVisitor Visitor);
}
