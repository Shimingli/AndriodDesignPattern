package com.shiming.andrioddesignpattern.decorator_model;

import android.util.Log;

/**
 * Created by shiming on 2017/9/17.
 */

public class ConcreteDecoratorB extends Decorator {
    /**
     * 构建方法传入组件
     *
     * @param component
     */
    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void getModel() {
        mComponent.getModel();

    }

    @Override
    protected void doLast() {
        Log.d("ConcreteDecoratorB","doLast");
    }

    @Override
    protected void doFirst() {
        Log.d("ConcreteDecoratorB","doFirst");
    }
}
