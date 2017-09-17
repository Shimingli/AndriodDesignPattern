package com.shiming.andrioddesignpattern.decorator_model;

/**
 * Created by shiming on 2017/9/17.
 * Decorator :装饰角色：持有一个抽象构件的引用，装饰对象接受所有客户端的请求，并把这些请求
 * 转发给真实的对象，这样就能够在真实对象调用前后增加新的功能
 */

public abstract class Decorator implements Component{

    protected final Component mComponent;

    /**
     * 构建方法传入组件
     * @param component
     */
    public Decorator(Component component) {
        mComponent = component;
    }
    public void doYouWantDo(){
        //在这之前 你想做什么
        doFirst();
       mComponent.getModel();
        doLast();
    }

    protected abstract void doLast();

    protected abstract void doFirst();

}
