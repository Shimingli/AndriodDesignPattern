package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.decorator_model.ConcreteComponent;
import com.shiming.andrioddesignpattern.decorator_model.ConcreteDecoratorA;
import com.shiming.andrioddesignpattern.decorator_model.ConcreteDecoratorB;

/**
 * Created by shiming on 2017/9/17.
 * 装饰者模式：装饰模式指的是在不必改变原类文件和使用继承的情况下，动态地扩展一个对象的功能。它是通过创建一个包装对象，也就是装饰来包裹真实的对象。
 * 装饰者模式是一种用于代替继承的技术，无需通过继承增加子类就能扩展对象的
 * 新功能，使用对象的关联关系代替继承关系，更加灵活，同时避免类型体系的快速膨胀
 * 动态的给一个对象添加额外的职责或者行为，就新增功能来说，Decorator 模式相比生成子类更加的灵活
 *
 * 在java中输入流和输出流就是这种的模式：
 * 需要使用装饰者模式的地方decorator
 * 1、需要在不影响其他对象的情况下，以动态、透明的方式给对象添加职责
 * 2、如果不合适使用子类来进行扩展的时候，可以的考虑使用装饰器的模式
 *
 * 优点
 * 1. Decorator模式与继承关系的目的都是要扩展对象的功能，但是Decorator可以提供比继承更多的灵活性。
 2. 通过使用不同的具体装饰类以及这些装饰类的排列组合，设计师可以创造出很多不同行为的组合。
 缺点编辑
 1. 这种比继承更加灵活机动的特性，也同时意味着更加多的复杂性。
 2. 装饰模式会导致设计中出现许多小类，如果过度使用，会使程序变得很复杂。
 3. 装饰模式是针对抽象组件（Component）类型编程。但是，如果你要针对具体组件编程时，就应该重新思考你的应用架构，以及装饰者是否合适。当然也可以改变Component接口，增加新的公开的行为，实现“半透明”的装饰者模式。在实际项目中要做出最佳选择。

 */

public class DecoratorModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.decorator_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView) getView().findViewById(R.id.tv);
        ConcreteComponent concreteComponent = new ConcreteComponent();
        ConcreteDecoratorA concreteDecoratorA=new ConcreteDecoratorA(concreteComponent);
        concreteDecoratorA.doYouWantDo();
        ConcreteDecoratorB concreteDecoratorB = new ConcreteDecoratorB(concreteComponent);
        concreteDecoratorB.doYouWantDo();

        textView.setText("日志输出 需要关心这几个类相互的关心 \n"
        +"09-17 13:35:19.558 15457-15457/? D/ConcreteDecoratorA: doFirst\n" +
                "09-17 13:35:19.558 15457-15457/? D/ConcreteComponent: getModel\n" +
                "09-17 13:35:19.558 15457-15457/? D/ConcreteDecoratorA: dolast\n" +
                "09-17 13:35:19.558 15457-15457/? D/ConcreteDecoratorB: doFirst\n" +
                "09-17 13:35:19.558 15457-15457/? D/ConcreteComponent: getModel\n" +
                "09-17 13:35:19.558 15457-15457/? D/ConcreteDecoratorB: doLast");
    }
}
