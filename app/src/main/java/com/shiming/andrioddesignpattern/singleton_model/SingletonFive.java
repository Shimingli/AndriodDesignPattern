package com.shiming.andrioddesignpattern.singleton_model;

/**
 * Created by shiming on 2017/9/10.
 * 内部类实现单例:使用内部类方式实现单例，
 * 既可以做到延迟加载，又不必使用同步关键字，
 * 是一种比较完善的实现
 * 延迟加载，线程安全（java中class加载时互斥的），也减少了内存消耗
 *
 */

public class SingletonFive {
    private SingletonFive(){

    }

    /**
     *单利模式大多有多线程的环境，使用内部类可以避免这个问题
     * jvm对一个类的初始化做了限制，同一时间只允许一个线程去
     * 初始化一个类，这样就从虚拟层面上避免了大部分的单利实现的问题
     */
    public static class SingletonHolder{
        private static SingletonFive singletonFive=new SingletonFive();
    }
    public SingletonFive getSingletonFive(){
        return SingletonHolder.singletonFive;
    }
}
