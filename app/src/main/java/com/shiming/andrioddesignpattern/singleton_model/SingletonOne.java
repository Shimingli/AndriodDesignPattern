package com.shiming.andrioddesignpattern.singleton_model;

/**
 * Created by shiming on 2017/9/10.
 * 懒汉式：
 * 优点：延迟加载，需要的时候才去加载
 * 缺点：线程不安全，在多线程中很容易出现不同步的情况
 */

public class SingletonOne {
    //持有私有的实例，防止被引用，此处
    private static volatile SingletonOne singleton=null;
    //私有化构造方法，防止被实例化
    private SingletonOne(){

    }
    //懒汉式，静态工厂的方法，创建实例
    public static SingletonOne getSingleton(){
        //线程不安全
        if (singleton==null){
            singleton=new SingletonOne();
        }
        return singleton;
    }


}
