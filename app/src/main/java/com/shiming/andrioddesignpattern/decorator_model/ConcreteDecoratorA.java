package com.shiming.andrioddesignpattern.decorator_model;

import android.util.Log;

/**
 * Created by shiming on 2017/9/17.
 */

public class ConcreteDecoratorA extends Decorator {

    /**
     * 构建方法传入组件
     *
     * @param component
     */
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    protected void doLast() {
        Log.d("ConcreteDecoratorA","dolast");
    }

    @Override
    protected void doFirst() {
        Log.d("ConcreteDecoratorA","doFirst");
    }


    @Override
    public void getModel() {
        mComponent.getModel();
    }
}
