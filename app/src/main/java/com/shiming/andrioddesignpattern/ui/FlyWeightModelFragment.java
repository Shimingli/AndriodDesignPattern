package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;

/**
 * Created by shiming on 2017/9/20.
 * 享元模式：享元模式是对象池的一种实现的方法，主要目的是用来尽可能的减少内存的使用
 * 适合于存在大量重复对象的场景，来缓存可以共享的对象，避免创建过多的对象的效果，已到达提升性能的作用
 * http://blog.csdn.net/happy_horse/article/details/51152232
 */

public class FlyWeightModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fly_weight_model_fragment,null,false);
    }
}
