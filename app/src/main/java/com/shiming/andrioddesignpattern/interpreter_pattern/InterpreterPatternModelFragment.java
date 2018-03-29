package com.shiming.andrioddesignpattern.interpreter_pattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.ui.BaseFragment;

import java.util.Stack;

import fr.expression4j.core.*;
import fr.expression4j.factory.ExpressionFactory;


/**
 * author： Created by shiming on 2018/3/28 16:31
 * mailbox：lamshiming@sina.com
 * interpreter:解释器模式,是一种比较少用的设计模式
 * 定义：给定一个语法，定义它的文法的一种表示，并定义一个解释器，这个解释器使用该标识来解释语言中的句子
 * 主要解决：对于一些固定文法构建一个解释句子的解释器
 * 如何使用：如果一种特定类型的问题发生的频率足够高，那么可能就值得将改问题的各个实例表达为一个简单语言中的句子。
 * 这样就可以构建一个解释器，该解释器通过解释这些句子来解决该问题
 * 优点：
 * 1、扩展性比较好
 * 2、增加了新的解释表达式的方式
 * 3、易于实现简单的文法
 * 4、解释器是一个简单语法分析工具，它显著的优点就是扩展性，修改语法规则只要修改相应的非终结符表达式就可以了，
 * 若扩展语法，则只要增加非终结符类就可以了
 * 缺点：
 * 1、可以利用的场景比较少，
 * 2、对于复杂的文法比较难以维护
 * 3、解释器模式会引起类膨胀
 * 4、解释器模式采用递归调用的方法
 *
 */

public class InterpreterPatternModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.interpreter_pattern_model_fragment_layout, null, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        ExpressionUtils.Test();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        // TODO: 2018/3/29  说来惭愧，找到了一个Expresson4J的jar包，想看下效果，但是没有搞起来    Caused by: java.lang.NoClassDefFoundError: org.apache.log4j.Logger
        //求大神解答

//                try {
//                   fr.expression4j.core.Expression expression = ExpressionFactory.createExpression("f()=2.4e-2");
//                    System.out.println("Expression name: " + expression.getName());
//                    System.out.println("Value of expression:" + expression.evaluate(null)
//                            .getRealValue());
//                } catch (Exception e) {
//                    System.out.println("Error: " + e);
//                }
//            }
//        }).start();
        //解释器模式的上下文的变量
        ExpressionContext expressionContext = new ExpressionContext();

        //通常一个语法容器，容纳一个具体的表达式，通常为一个集合
        Stack<Expression> stack=null;

        for (;;){
            //进行语法判断，并产生递归调用

        }
        //删除堆栈顶部的对象并返回对象作为该函数的值
        //产生一个完整的语法树，由各个具体的语法分析进行解析
//        TerminalExpression  pop = stack.pop();
//         //具体元素进入场景
//        pop.interpreter(expressionContext);


    }
}
