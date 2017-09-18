package com.shiming.andrioddesignpattern.proxy_model;

/**
 * Created by shiming on 2017/9/17.
 *
 * 静态代理：代理类在编译的时候就生成了，也就是代理类在编译完成后已经存在了.class 文件
 */

public final class StaticProxySubject implements StaticSubject {

    private final StaticRealSubject mRealSubject;

    public StaticProxySubject() {
        mRealSubject = new StaticRealSubject();
    }

    @Override
    public void request() {
        mRealSubject.request();
    }
}
