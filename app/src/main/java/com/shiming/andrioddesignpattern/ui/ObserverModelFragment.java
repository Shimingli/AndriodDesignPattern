package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.observer_model.ConcreteObserver;
import com.shiming.andrioddesignpattern.observer_model.ConcreteSubject;
import com.shiming.andrioddesignpattern.utils.ToastUtil;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/22
 * @des Observer 观察者模式：发布和订阅模式 publish - subscribe 模式，属于行为型模式的一种，它定义了一种一对多的依赖关系
 * 让多个观察者对象同时监听某一个主题对象，这个主题对象在状态变化时候，会通知所有的观察对象，使他们能够自动更新自己
 * 使用的场景：关联行为场景，需要注意的是，关联行为是可拆分的，而不是组合关系
 * 事件多级触发的场景
 * 跨系统的消息交换的场景，如消息队列，事件总线的处理机制
 * 优点，解耦，让耦合的双方都依赖于抽象，从而使得各自的变换都不会影响另外一边的变换
 * 缺点：在应用观察者模式时候需要考虑一下开发的效率和运行效率的问题，程序中包括一个被观察者和多个观察者
 * ，开发或者是调试内容比较复杂，而且在java中消息的通知一般是顺序执行，那么一个观察者消耗的时间过长的话
 * 在这种情况下，一般会采用异步的实现
 * 安卓的源码中很多也使用了观察者模式，比如onclickListener contentObserver Observable
 * 组件通信RXjava和 Rxandroid EventBus 最常用的还是Adapter中的notifyDataSetChanged（）方法
 * 可以通过BaseAdapter的源码分析。当listview的数据发生了变化时候，我们调用adapter的notifyDataSetChanged（）
 * 的方法，这个方法又会调用观察者AdapterDataSetObserver的onchanged的方法，onchanged的方法
 * 又会调用requestLayout（）的方法来重新布局，这就是观察者很好的例子
 *
 * 注意事项： 1、JAVA 中已经有了对观察者模式的支持类。 2、避免循环引用。 3、如果顺序执行，某一观察者错误会导致系统卡壳，一般采用异步方式。
 *
 *
 */
public class ObserverModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.observer_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ConcreteObserver a = new ConcreteObserver("A");
        final ConcreteObserver b = new ConcreteObserver("B");
        final ConcreteObserver c = new ConcreteObserver("C");
        final ConcreteObserver d = new ConcreteObserver("D");


        final ConcreteSubject concreteSubject = new ConcreteSubject();
        concreteSubject.attach(a);
        concreteSubject.attach(b);
        concreteSubject.attach(c);
        concreteSubject.attach(d);

        Button click = (Button) getView().findViewById(R.id.btn_click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concreteSubject.notifyMessage("我要取媳妇了哦");
                String messagea = a.getMessage();
                String messageb = b.getMessage();
                String messagec = c.getMessage();
                String messaged= d.getMessage();
                ToastUtil.showShort(getContext(),messagea+messageb+messagec+messaged);


            }
        });

    }
}
