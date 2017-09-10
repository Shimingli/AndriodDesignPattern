package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.factory_model.ChildrenToy;
import com.shiming.andrioddesignpattern.factory_model.IToy;
import com.shiming.andrioddesignpattern.factory_model.ToyFactory;

/**
 * Created by shiming on 2017/9/10.创建型模式，工厂方法模式
 *工厂方法模式是一个很好的设计模式，它遵循了一个“尽可能让事情保持抽象的原则，
 * 松耦合的设计原则也能够很好的符合开闭原则，将类的实例化推迟到子类，同时也摈弃了简单工厂模式的缺点。
 　但是同时工厂方法模式也有一些缺点，每次我们为工厂方法添加新的产品时就要编写一个新的产品类
 ，同时还要引入抽象层，当产品种类非常多时，会出现大量的与之对应的工厂对象，
 这必然会导致类结构的复杂化，所以对于简单的情况下，使用工厂方法模式就需要考虑是不是有些“重”了。

 好处：假如在项目中某个对象被new了100次，以后业务逻辑发生改变了，构造方法多了一个参数，你不会把所有的new的代码
 去边删掉了把，用过用过程模式的话，你只需要换一个工程就好了
 */

public class FactoryMethodModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.first_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showTitleBar(false);
        TextView tvShow = (TextView) getView().findViewById(R.id.tv);
        ToyFactory toyFactory=new ToyFactory();
        IToy creator = toyFactory.creator(ChildrenToy.class);
        tvShow.setText("通过工厂模式创建的玩具:"+"\n"+creator.getToyName()+"\n"+creator.getToyPrice()+"\n");
        creator.doWhat();
    }
}
