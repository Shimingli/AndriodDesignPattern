package com.shiming.andrioddesignpattern.interpreter_pattern;

import com.shiming.andrioddesignpattern.state_model.Context;

/**
 * author： Created by shiming on 2018/3/29 11:22
 * mailbox：lamshiming@sina.com
 */

/**
 *非终结者表达式：都代表一个文法规则，并且每个文法规则都只关心自己周边的文法规则结果
 * 因此就产生了每个终结者表达式调用自己周边的非终结者表达式，然后最终，最小的文法规则就是终结者
 * 表达式，终结者表达式就是如此，不能再参与比自己更小的文法的运算了
 */
public class NonTerminalExpression extends Expression{
    //每个非终结者表达式都会对其他表达式产生依赖
    public NonTerminalExpression(Expression...expressions){

    }
    @Override
    public Object interpreter(ExpressionContext context) {
        //进行文法处理
        return null;
    }
}
