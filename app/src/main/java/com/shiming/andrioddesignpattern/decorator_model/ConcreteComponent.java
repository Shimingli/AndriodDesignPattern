package com.shiming.andrioddesignpattern.decorator_model;

import android.util.Log;

/**
 * Created by shiming on 2017/9/17.
 * Component：组件  concrete ：具体的
 * 具体组件，实现组件接口，Component 该对像通常是被装饰器装饰的原始对象
 * 可以给这个对象添加职责
 */

public class ConcreteComponent implements Component{
    @Override
    public void getModel() {
        Log.d("ConcreteComponent","getModel");
    }
}
