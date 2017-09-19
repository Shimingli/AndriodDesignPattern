package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.bridge_model.Circle;
import com.shiming.andrioddesignpattern.bridge_model.Rectangle;
import com.shiming.andrioddesignpattern.bridge_model.WhiteColor;

/**
 * Created by shiming on 2017/9/18.
 * 桥接模式： 分离抽象接口及其实现的部分，提高比继承更好的解决方法
 * 桥接模式提高了系统的可扩充性，在两个变化的维度中任意扩张一个维度，都不需要修改原来的维度
 * 缺点：
 * 桥接模式会增加系统的理解与设计难度，由于聚合关联关系建立在抽象层，要求开发者针对
 * 抽象类进行设计与编程
 * 桥接模式要求正确识别出系统中两个独立变化的维度，使用有一定的局限性
 *
 * 桥接模式使用的场景：如果一个系统在构建的抽象画的角色和具体化角色之间增加更多的灵活性
 * 避免在两个层次之间建立静态的继承联系，通过桥接模式可以使他们在抽象层建立一个关系
 * 对于那些不希望使用继承或因为多层次继承导致系统类的个数增加，桥接模式尤为的使用
 * 一个类存在两个独立的变化的维度，且这两个维度都需要进行扩张
 */

public class BridgeModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.brigde_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //需要那种颜色的，其实这里已经通过后面的draw方法把需要绘制的形状已经告诉它了
        WhiteColor whiteColor = new WhiteColor();
        //需要绘制的形状，这个形状就是内部实现的方法就是拿着上面颜色的子类方法回调
        Circle circle = new Circle();
        //发生关系
        circle.setShapColor(whiteColor);
        //绘制最终的形状
        String draw = circle.draw();
        //WhiteColor Circle
        System.out.println("shiming--draw=="+draw);

        Rectangle rectangle = new Rectangle();
        rectangle.setShapColor(whiteColor);
        String draw1 = rectangle.draw();
        //WhiteColor Rectangle
        System.out.println("shiming--draw=="+draw1);

//        ListAdapter 继承了Adapter
//        AbsListView setAdapter（ Adapter ）
//        ListView setAdapter( ListAdapter);
    }
}
