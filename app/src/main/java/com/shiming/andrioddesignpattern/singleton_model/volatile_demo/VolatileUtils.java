package com.shiming.andrioddesignpattern.singleton_model.volatile_demo;

/**
 * author： Created by shiming on 2018/5/14 14:18
 * mailbox：lamshiming@sina.com
 */
/*
05-14 15:00:06.929 17224-17224/? I/System.out: shiming--------》VolatileDemoClass------》10000
05-14 15:00:06.938 17224-17224/? I/System.out: shiming--------》VolatileSynchronizedDemoClass------》10000
05-14 15:00:06.947 17224-17224/? I/System.out: shiming--------》VolatileLockDemoClass------》10000
05-14 15:00:06.949 17224-17224/? I/System.out: shiming--------》VolatileAtomicIntegerDemoClass------》10000
05-14 15:00:08.601 17224-17224/? I/System.out: shiming--------》VolatileDemoClass------》9818
05-14 15:00:08.619 17224-17224/? I/System.out: shiming--------》VolatileSynchronizedDemoClass------》10000
05-14 15:00:08.621 17224-17224/? I/System.out: shiming--------》VolatileLockDemoClass------》10000
05-14 15:00:08.622 17224-17224/? I/System.out: shiming--------》VolatileAtomicIntegerDemoClass------》10000
05-14 15:00:09.520 17224-17224/? I/System.out: shiming--------》VolatileDemoClass------》8991
05-14 15:00:09.525 17224-17224/? I/System.out: shiming--------》VolatileSynchronizedDemoClass------》10000
05-14 15:00:09.533 17224-17224/? I/System.out: shiming--------》VolatileLockDemoClass------》10000
05-14 15:00:09.537 17224-17224/? I/System.out: shiming--------》VolatileAtomicIntegerDemoClass------》10000
05-14 15:00:10.317 17224-17224/? I/System.out: shiming--------》VolatileDemoClass------》9705
05-14 15:00:10.322 17224-17224/? I/System.out: shiming--------》VolatileSynchronizedDemoClass------》10000
05-14 15:00:10.328 17224-17224/? I/System.out: shiming--------》VolatileLockDemoClass------》10000
05-14 15:00:10.331 17224-17224/? I/System.out: shiming--------》VolatileAtomicIntegerDemoClass------》10000
 */
public class VolatileUtils {
    //输出的结果，如上！
    public static void doFrist() {
        VolatileDemoClass.main();
        VolatileSynchronizedDemoClass.main();
        VolatileLockDemoClass.main();
        VolatileAtomicIntegerDemoClass.main();
        VolatileUtils volatileUtils = new VolatileUtils();
        volatileUtils.test();
    }

    // TODO: 2018/5/14  没有解决啊 感觉 
    //5个线程内部打印hello和word，hello在前，要求提供一种方法使得5个线程先全部打印出hello后再打印5个word。
    public void test() {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("shiming hello---");

//                    hello();
//                    word();
                }
            }).start();

            while (Thread.activeCount() > 1) { //保证前面的线程都执行完
                System.out.println("shiming word---");
                Thread.yield();
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("shiming word---");
//                    hello();
//                    word();
                }
            }).start();
        }
    }

    /*
    * 为了增加代码可读性，这里用了两个Boolean变量作为正在执行方法的标记
    * Hello_WillRun如果等于true，表明Hello()方法即将要获得this锁，否则就保持wait
    * 因为两个方法是交替执行，同一时间只有一个执行，所以两个变量必须保持互反：Hello_WillRun=!Word_WillRun
    * 默认将先执行的那个设为true，后执行的设为false
    */
    Boolean Hello_WillRun = true;
    Boolean Word_WillRun = false;

    /*两个方法拥有同样的锁：this*/
    public synchronized void hello() {
        /* 如果Hello()不是接下来将要运行的状态，即：!Hello_WillRun，那么保持等待wait()
        while用于防止线程假醒后，顺序往下执行输出功能，从而破坏交替输出*/
        while (!Hello_WillRun) {
            try {
                /* Hello()进行等待
                 * 调用wait()和notify()的对象必须和synchronized锁对象一致，因此这里用this*/
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*执行Hello()核心功能*/
        System.out.println("shiming " + Thread.currentThread().getName() + "say:Hello");

        /* Hello()执行完毕，设置下一步的标记状态值 */
        Hello_WillRun = false;
        Word_WillRun = true;
        /*唤醒另一个线程*/
        this.notify();

    }

    /*两个方法拥有同样的锁：this*/
    public synchronized void word() {
        while (!Word_WillRun) {
            try {
                /* Word()进行等待
                 * 调用wait()和notify()的对象必须和synchronized锁对象一致，因此这里用this*/
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*执行Word()核心功能*/
        System.out.println("shiming " + Thread.currentThread().getName() + "say:World");
        /* Word()执行完毕，设置下一步的状态值 */
        Hello_WillRun = true;
        Word_WillRun = false;
        /*唤醒另一个线程*/
        this.notify();

    }
}
