package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.singleton_model.SingletonSix;

/**
 * Created by shiming on 2017/9/10.
 * 好处是：提供了对外唯一的受控访问
 * 由于系统中只存在一个对象，因此可以节约系统资源，对于频繁需要创建和销毁的对象无意是提高了系统的性能
 * 坏处：单利模式没有抽象层，因此单利类的扩展很麻烦
 * 单利类职责过重，在一定程度上违背了单一职责的原则
 * 滥用单利带来一些负面问题，如果实例化对象长时间不被利用，系统会认为是垃圾而被回收，这将导致对象状态的丢失
 *
 *
 *  保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 *
 *  优点
 一、实例控制
 单例模式会阻止其他对象实例化其自己的单例对象的副本，从而确保所有对象都访问唯一实例。
 二、灵活性
 因为类控制了实例化过程，所以类可以灵活更改实例化过程。

 缺点
 一、开销
 虽然数量很少，但如果每次对象请求引用时都要检查是否存在类的实例，将仍然需要一些开销。可以通过使用静态初始化解决此问题。
 二、可能的开发混淆
 使用单例对象（尤其在类库中定义的对象）时，开发人员必须记住自己不能使用new关键字实例化对象。因为可能无法访问库源代码，因此应用程序开发人员可能会意外发现自己无法直接实例化此类。
 三、对象生存期
 不能解决删除单个对象的问题。在提供内存管理的语言中（例如基于.NET Framework的语言），只有单例类能够导致实例被取消分配，因为它包含对该实例的私有引用。在某些语言中（如 C++），其他类可以删除对象实例，但这样会导致单例类中出现悬浮引用。。
 */

public class SingletonModelFragment extends BaseFragment{
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.singleton_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView) getView().findViewById(R.id.tv);
        SingletonSix.INSTANCE.doThing();
        textView.setText(
                "SingletonOne"+"懒汉式线程不安全\n"+
                        "SingletonTwo"+"加入同步锁，线程安全，效率低\n"+
                        "SingletonThird"+"加入同步锁，双重判断，但是在不同平台有安全隐患，原因是：jvm编译的过程中会出现指令重排的优化过程\n"+
                        "SingletonFourth"+"饿汉式,占空间，基本上没有在项目中使用到\n"+
                        "SingletonFive"+"内部类实现关键字：做到了线程安全，有不必使用同步关键字，因为在jvm只允许一个线程去初始化一个类\n"+
                        "SingetonSix"+"枚举实现单利：这个很有意思，可以看看这个类"

        );
    }
}
