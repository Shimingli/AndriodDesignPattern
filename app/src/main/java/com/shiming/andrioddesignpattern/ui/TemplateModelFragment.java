package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.template_model.ConcreteClass;
import com.shiming.andrioddesignpattern.template_model.ConcreteClassTwo;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/22
 * @des 模板方法模式：定义一个算法中的操作框架，而将一些步骤延迟到子类中，使得子类可以不改变算法额结构即可定义该
 * 算法的某些的特定的步骤
 * 优点：
 * 1、封装不变的代码，扩展可变得部分，把认为不变的部分的算法封装到父类中实现，而可变部分的则可以通过继承来继续扩张
 * 2、提取公众的代码，便于维护
 * 3、行为由父类，子类实现
 *
 * 缺点：按照设计的习惯，抽象类负责最抽象、最一般的事物属性和方法，实现类负责完成具体的事物属性和方法。但是模板方法正好相反
 * 子类执行的结果影响了父类的结果，会增加在这种模式阅读下的难度
 *
 * 模板模式使用的场景：
 * 1、多个子类有公有的方法，并且逻辑基本相同
 * 2、重要复杂的算法，可以把核心算法设计模板方法，周边的相关细节功能则由各个子类实现
 * 3、重构时候，模板方法是一个经常使用的方法，把相同的代码抽取到父类中，然后通过构造函数去约束其行为
 *
 *
 *
 */
public class TemplateModelFragment extends BaseFragment {

    private Button mClickBtn;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.template_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mClickBtn = (Button) getView().findViewById(R.id.btn_click);
        mClickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConcreteClass concreteClass = new ConcreteClass();
                concreteClass.templateMethod(getContext());
                ConcreteClassTwo concreteClassTwo = new ConcreteClassTwo();
                concreteClassTwo.templateMethod(getContext());
            }
        });
    }
}
