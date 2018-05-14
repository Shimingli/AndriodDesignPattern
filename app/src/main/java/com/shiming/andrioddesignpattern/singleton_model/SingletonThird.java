package com.shiming.andrioddesignpattern.singleton_model;

/**
 * Created by shiming on 2017/9/10.
 * 单利的双重检验锁：
 * 优点：在并发量不多，安全性不高的情况下或许能很完美运行单利模式
 * 缺点：不同平台编译工程中可能有会严重的安全隐患
 * 在android图像开源项目Android-Universal-Image-Loader
 * （https://github.com/nostra13/Android-Universal-Image-Loader）
 * 中使用的是这种方式。
 */

public class SingletonThird {
    /**
     * 在网上找到这么一句话：观察加入volatile关键字和没有加入volatile关键字时所生成的汇编代码发现，
     * 加入volatile关键字时，会多出一个lock前缀指令lock前缀指令实际上相当于一个内存屏障（也成内存栅栏），内存屏障会提供3个功能：
     * 1）它确保指令重排序时不会把其后面的指令排到内存屏障之前的位置，也不会把前面的指令排到内存屏障的后面；即在执行到内存屏障这句指令时，
     * 在它前面的操作已经全部完成；
     * 2）它会强制将对缓存的修改操作立即写入主存；
     * 3）如果是写操作，它会导致其他CPU中对应的缓存行无效。
     2、synchronized（记得双重的判断）关键字是防止多个线程同时执行一段代码，那么就会很影响程序执行效率，
     而volatile关键字在某些情况下性能要优于synchronized，至于那些情况我也不太明白，因为涉及到java内存模型的相关概念，抛砖引玉一下：
     */
    //volatile  防止指令重排
    /**
     * 对于可见性，Java提供了volatile关键字来保证可见性。
     当一个共享变量被volatile修饰时，它会保证修改的值会立即被更新到主存，当有其他线程需要读取时，它会去内存中读取新值。
      */
    /**
     * synchronized关键字是防止多个线程同时执行一段代码，那么就会很影响程序执行效率，
     * 而volatile关键字在某些情况下性能要优于synchronized，但是要注意volatile关键字是无法替代synchronized关键字的，
     * 因为volatile关键字无法保证操作的原子性
     */
    private static volatile  SingletonThird singletonThird=null;


    private SingletonThird(){

    }

    /**
     * 因为每次调用实例都需要判断同步锁，很多项目包括很多人都是用这种的
     * 双重判断校验的方法，这种的方法看似很完美的解决了效率的问题，但是它
     * 在并发量不多，安全性不太高的情况下能完美的运行，但是，
     * 在jvm编译的过程中会出现指令重排的优化过程，这就会导致singleton实际上
     * 没有被初始化，就分配了内存空间，也就是说singleton！=null但是又没有被初始化，
     * 这就会导致返回的singletonthird返回的是不完整的
     * 参考：http://www.360doc.com/content/11/0810/12/1542811_139352888.shtml
     * @return
     */
    //原子性：即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。
    //可见性:是指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。
    //有序性：即程序执行的顺序按照代码的先后顺序执行。
    /**
     * Java里面，可以通过volatile关键字来保证一定的“有序性”（具体原理在下一节讲述）。另外可以通过synchronized和Lock来保证有序性，
     * 很显然，synchronized和Lock保证每个时刻是有一个线程执行同步代码，相当于是让线程顺序执行同步代码，自然就保证了有序性。
     */
    public static SingletonThird getSingletonThird(){
        //synchronized和Lock来实现。由于synchronized和Lock能够保证任一时刻只有一个线程执行该代码块，那么自然就不存在原子性问题了，从而保证了原子性。
        if (singletonThird==null){
            synchronized (SingletonThird.class){
                if (singletonThird==null){
                    singletonThird=new SingletonThird();
                }
            }
        }
        return singletonThird;
    }
}
