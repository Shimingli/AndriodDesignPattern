package com.shiming.andrioddesignpattern.singleton_model.volatile_demo;

import junit.framework.Test;

/**
 * author： Created by shiming on 2018/5/14 14:16
 * mailbox：lamshiming@sina.com
 */

public class VolatileDemoClass {
    //volatile关键字能保证可见性没有错，但是上面的程序错在没能保证原子性.所以输出的结果，才不是10000
        public volatile int inc = 0;

        public void increase() {
            inc++;
        }
        /*
 05-14 14:25:49.405 25827-25827/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》10000
05-14 14:25:50.963 25827-25827/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》9822
05-14 14:25:52.303 25827-25827/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》10000
05-14 14:25:53.589 25827-25827/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》10000
05-14 14:25:54.786 25827-25827/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》10000
05-14 14:25:55.907 25827-25827/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》10000
05-14 14:25:57.019 25827-25827/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》8877
05-14 14:25:58.129 25827-25827/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》10000
05-14 14:25:59.104 25827-25827/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》9467
05-14 14:26:00.169 25827-25827/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》10000
05-14 14:26:01.248 25827-25827/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》9939
05-14 14:26:02.309 25827-25827/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》9436
         */
        //通过重启App，实现的，可以发现这个值不能保证准确
        public static void main( ) {
            final VolatileDemoClass test = new VolatileDemoClass();
            for(int i=0;i<10;i++){
                new Thread(){
                    public void run() {
                        for(int j=0;j<1000;j++)
                            test.increase();
                    }
                }.start();
            }

            /*
 05-14 14:29:51.858 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》2000
05-14 14:29:56.571 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》2000
05-14 14:29:58.236 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》2000
05-14 14:29:59.614 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》3000
05-14 14:30:02.125 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》0
05-14 14:30:03.619 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》0
05-14 14:30:05.012 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》0
05-14 14:30:06.555 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》3000
05-14 14:30:08.513 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》2000
05-14 14:30:09.740 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》6501
05-14 14:30:10.860 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》5941
05-14 14:30:13.190 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》1000
05-14 14:30:14.776 28782-28782/com.shiming.andrioddesignpattern I/System.out: shiming--------》VolatileUtils------》0
             */
            // 把下面的方法注释掉的话，输出的是上面的结果
            /**
             * 如果还有线程在运行，主线程就让出CPU资源，知道所有的子线程都运行完了，
             * 主线程在继续往下执行
             */
            while(Thread.activeCount()>1) {  //保证前面的线程都执行完
                Thread.yield();
            }
            System.out.println("shiming--------》VolatileDemoClass------》"+test.inc);
        }

}
