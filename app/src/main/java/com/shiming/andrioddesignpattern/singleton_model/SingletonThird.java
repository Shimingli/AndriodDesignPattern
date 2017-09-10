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
    private static SingletonThird singletonThird=null;
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
    public static SingletonThird getSingletonThird(){
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
