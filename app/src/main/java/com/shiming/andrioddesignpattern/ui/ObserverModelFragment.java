package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/22
 * @des Observer 观察者模式：发布和订阅模式 publish - subscribe 模式，属于行为型模式德一种，它定义了一种一对多的依赖关系
 * 让多个观察者对象同时监听某一个主题对象，这个主题对象在状态变化时候，会通知所有的观察对象，使他们能够自动更新自己
 *
 * http://blog.csdn.net/itachi85/article/details/50773358
 */
public class ObserverModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.observer_model_fragment_layout,null,false);
    }
}
