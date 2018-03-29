package com.shiming.andrioddesignpattern.interpreter_pattern;



/**
 * author： Created by shiming on 2018/3/29 11:17
 * mailbox：lamshiming@sina.com
 *
 * Terminal :终结者
 */
//终结符表达式  主要是处理场景元素和数据的转换
    //如 a+b+c 中 的 "a" "b" "c"
public class TerminalExpression extends Expression {
    //通常终结表达式只有一个，但是有多个对象
    @Override
    public Object interpreter(ExpressionContext context) {
        return null;
    }
}
