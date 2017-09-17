package com.shiming.andrioddesignpattern.proxy_model;

/**
 * Created by shiming on 2017/9/17.
 * 动态代理：在运行时候才会生成代理类，和静态代理不同在编译阶段不会生成实现的.class文件，而是在
 * 程序运行时候生成类字节码文件，然后通过ClassLoader加载到jvm中，简而言之，代理类是在运行时根据需要代理
 * 的接口等一些列参数动态生成的
 */

public interface DynamicSubject {
    void request();
}
