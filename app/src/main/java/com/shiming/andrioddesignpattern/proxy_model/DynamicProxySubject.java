package com.shiming.andrioddesignpattern.proxy_model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by shiming on 2017/9/17.
 * InvocationHandler:该接口有一个invoke方法,使用动态代理时都需要自定义一个类然后实现
 * 这个接口，自定义类就是我们调用处理器，我们可以在这实现我们需要的任务，当运行代理类调用任何方法
 * 都会回调invoke方法
 */

public class DynamicProxySubject implements InvocationHandler {

    private final DynamicRealSubject mDynamicRealSubject;

    public DynamicProxySubject() {
        mDynamicRealSubject = new DynamicRealSubject();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(mDynamicRealSubject, args);
        return invoke;
    }
}
