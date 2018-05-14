package com.shiming.andrioddesignpattern.singleton_model.volatile_demo;


/**
 * author： Created by shiming on 2018/5/14 14:40
 * mailbox：lamshiming@sina.com
 */

public class VolatileSynchronizedDemoClass {

    public  int inc = 0;
    //保证原子性
    public synchronized void increase() {
        inc++;
    }

    public static void main() {
        final VolatileSynchronizedDemoClass test = new VolatileSynchronizedDemoClass();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                }
            }.start();
        }
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println("shiming--------》VolatileSynchronizedDemoClass------》"+test.inc);
    }
}
