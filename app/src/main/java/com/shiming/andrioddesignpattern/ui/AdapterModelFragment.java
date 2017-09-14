package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.adapter_model.Adaptee;
import com.shiming.andrioddesignpattern.adapter_model.Adapter;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/14
 * @des 适配器模式：手机电源的适配器，笔记本电脑的充电器，你如果要给笔记本充电，你肯定不会带个充电宝吧，特别好理解
 * 将一个类的接口转换成客服希望的另外的一个接口，adapter模式使得原来由于接口不兼容而不能一起工作的那些类可以一起工作
 *
 * 优点：通过适配器，客户端可以调用同一接口，对客户端来讲说是透明的，这样更加简单，更直接，更加紧凑
 * 复用了现成的类，解决了现存类和复用环境要求不一致的问题
 * 将目标类和适配器者类解耦，通过引入一个适配器重用现有的适配器类，而无需修改原有代码
 * 一个对象适配器可以把多个不同的适配器类适配到同一个目标，也就是说，同一个适配器可以把适配者类和它的子类都适配到目标的接口
 * 缺点：对于对象适配器来说，更换适配器的实现的过程比较复杂，代价有点大
 * 使用的场景 ：
 * 系统需要使用现在的类，而这些类的接口不符合系统的接口
 * 想要建立一个可以重用的类，用于一些彼此之间没有太大的关联的一些类，包括一些可能在将来引进来的工作
 * 两个类所做的事情相同或者是相似，但是具有不同接口的时候
 * 比如旧的模块已经实现了一些功能，但是客服端只能以另外接口的形式访问，但我们不希望手动更改原来的类
 * 使用第三方组件，组件接口定义和自己定义的不同，不希望修改自己的接口的时候，但是要使用第三方组件的接口的功能。
 */
public class AdapterModelFragment extends BaseFragment {

    private TextView mTvDes;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.adapter_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTvDes = (TextView) getView().findViewById(R.id.tv);
        Adapter adapter = new Adapter(new Adaptee());
        mTvDes.setText("我是适配器模式得到的值："+adapter.request());
    }
}
