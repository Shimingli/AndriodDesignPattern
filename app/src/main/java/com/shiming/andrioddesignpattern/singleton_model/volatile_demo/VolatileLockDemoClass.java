package com.shiming.andrioddesignpattern.singleton_model.volatile_demo;

import junit.framework.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * author： Created by shiming on 2018/5/14 14:53
 * mailbox：lamshiming@sina.com
 * des:VolatileSynchronizedDemoClass 差不多
 */

public class VolatileLockDemoClass {
    public  int inc = 0;
    //有兴趣的话，可以看下源码级的东西，这个和关键字（Synchronized 差不多，但是还很远
    //）
    Lock lock = new ReentrantLock();

    public  void increase() {
        lock.lock();
        try {
            inc++;
        } finally{
            lock.unlock();
        }
    }

    public static void main() {
        final VolatileLockDemoClass test = new VolatileLockDemoClass();
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
        System.out.println("shiming--------》VolatileLockDemoClass------》"+test.inc);
    }
}
