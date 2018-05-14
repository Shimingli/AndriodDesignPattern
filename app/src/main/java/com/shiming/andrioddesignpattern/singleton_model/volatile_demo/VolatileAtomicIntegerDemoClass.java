package com.shiming.andrioddesignpattern.singleton_model.volatile_demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * author： Created by shiming on 2018/5/14 14:58
 * mailbox：lamshiming@sina.com
 */

public class VolatileAtomicIntegerDemoClass {
    public AtomicInteger inc = new AtomicInteger();

    public  void increase() {
        inc.getAndIncrement();
    }

    public static void main() {
        final VolatileAtomicIntegerDemoClass test = new VolatileAtomicIntegerDemoClass();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println("shiming--------》VolatileAtomicIntegerDemoClass------》"+test.inc);
    }
}
