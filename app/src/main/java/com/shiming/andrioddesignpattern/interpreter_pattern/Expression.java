package com.shiming.andrioddesignpattern.interpreter_pattern;


/**
 * author： Created by shiming on 2018/3/29 11:05
 * mailbox：lamshiming@sina.com
 */
//抽象表达式
//抽象表达式是生产语法集合（语法树）的关键，每个语法集合完成指定语法解析任务，他是
//通过递归调用的方式，最终由最小的语法单元进行解析完成
public abstract class Expression {
    //每个表达式必须有一个解析任务
    public abstract Object interpreter(ExpressionContext context);
}
