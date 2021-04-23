package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.state_model.NewContext;

/**
 * Created by shiming on 2017/10/15.
 * 一个对象内在状态发生了改变时候允许改变其行为，这个对象看起来像是改变了其类
 * 状态模式封装的非常好，状态的变更引起了行为的变更，从外部看起来就好像这个对象对应的类发生了改变一样
 * <p>
 * 优点：避免了过多的 switch。。。case  或者是if。。。else语句的使用，避免了程序的复杂程度，其实是很好的
 * 体现了开闭原则和第一职责原则，每个状态都是一个子类，增加状态的就增加子类，修改状态的话，就修改一个子类就行了
 * 缺点：是状态过多的话，类膨胀
 */
public class StateModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.state_model_fragment_layout, null, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NewContext context = new NewContext();

        context.setLiftState(NewContext.ClosingState);
        context.open();
        context.close();
        context.run();
        context.stop();

        System.out.println("--------------------------------->");
        context.setLiftState(NewContext.RunningState);
        context.open();
        context.close();
        context.run();
        context.stop();

    }
}
