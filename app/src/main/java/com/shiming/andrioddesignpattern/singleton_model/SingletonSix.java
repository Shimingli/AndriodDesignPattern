package com.shiming.andrioddesignpattern.singleton_model;

import java.io.Serializable;

/**
 * Created by shiming on 2017/9/10.
 * 枚举的方法：
 * 枚举单例模式是java5之后创建单例最好的方法
 * 1、代码简洁：枚举单例模式代码简洁
 * 2、枚举单利可以自己处理序列化：传统的单利模式的另外一个问题是你实现了Serializable这个接口了
 * 他们就不是单利了，因为readObject（）方法总是返回一个新的实例对象，就像java中的构造器一样
 * 3.枚举单利线程是安全的，因为枚举的线程是安全的
 */

public enum  SingletonSix implements Serializable{
    INSTANCE;
    public String doThing(){
        /**
         * 在java1.5开始，无偿提供序列化机制，防止枚举
         * 多次实例化，即是在面对复杂的序列化和反射攻击的时候
         */
        return "我是枚举实现的单利";
    }
    private Object readResolve(){
        return INSTANCE;
    }
}
