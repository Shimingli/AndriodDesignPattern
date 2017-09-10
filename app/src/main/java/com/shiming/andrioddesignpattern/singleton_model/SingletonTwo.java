package com.shiming.andrioddesignpattern.singleton_model;

/**
 * Created by shiming on 2017/9/10.
 * 在第一种的基础上加上一个同步锁，
 * 优点：解决了线程不安全的问题
 * 缺点：效率低，每次调用实例的时候都要判断同步锁
 */

public class SingletonTwo {
    private static SingletonTwo singletonTwo=null;
    private SingletonTwo(){

    }
    //解决线程安全的的问题1.在方法中加入同步锁
    public static synchronized SingletonTwo getSingletonTwo(){
        if (singletonTwo==null){
            singletonTwo=new SingletonTwo();
        }
        return singletonTwo;
    }
    //解决线程安全的问题，2，每次调用实例的都会加载
    private static SingletonTwo getSingletonTwoT(){
        synchronized (SingletonTwo.class){
            if (singletonTwo==null){
                singletonTwo=new SingletonTwo();
            }
            return singletonTwo;
        }
    }
    //InputMethodManager，AccessibilityManager
    // 等都是使用这种单例模式
    /**
     public static InputMethodManager getInstance() {
     synchronized (InputMethodManager.class) {
     if (sInstance == null) {
     IBinder b = ServiceManager.getService(Context.INPUT_METHOD_SERVICE);
     IInputMethodManager service = IInputMethodManager.Stub.asInterface(b);
     sInstance = new InputMethodManager(service, Looper.getMainLooper());
     }
     return sInstance;
     }
     }
     */
}
