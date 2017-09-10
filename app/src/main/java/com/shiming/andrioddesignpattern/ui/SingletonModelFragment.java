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
