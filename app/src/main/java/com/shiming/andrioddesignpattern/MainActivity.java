package com.shiming.andrioddesignpattern;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shiming.andrioddesignpattern.singleton_model.volatile_demo.VolatileUtils;

import java.sql.SQLOutput;

/**
创建型模式，共五种：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。
 结构型模式，共七种：适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。
 行为型模式，共十一种：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、
 命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。

 正如《设计模式》的作者GoF对访问者模式的描述：大多数情况下，你并需要使用访问者模式，但是当你一旦需要使用它时，那你就是真的需要它了。
 当然这只是针对真正的大牛而言。在现实情况下（至少是我所处的环境当中），很多人往往沉迷于设计模式，他们使用一种设计模式时，
 从来不去认真考虑所使用的模式是否适合这种场景，而往往只是想展示一下自己对面向对象设计的驾驭能力。编程时有这种心理，
 往往会发生滥用设计模式的情况。所以，在学习设计模式时，一定要理解模式的适用性。必须做到使用一种模式是因为了解它的优点，
 不使用一种模式是因为了解它的弊端；而不是使用一种模式是因为不了解它的弊端，不使用一种模式是因为不了解它的优点。

 ps: 2020年3月9日 17：09提交这个设计代码   come on  加油加油
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(" shiming onCreate  ");
        findViewById(R.id.btn_creator_model).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CreatorModelActivity.class));
            }
        });
        findViewById(R.id.btn_structural_model).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,StructuralModelActivity.class));
            }
        });
        findViewById(R.id.btn_behavioral_model).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BehaviorModelActivity.class));
            }
        });

        /*
        volatile 关键字的Demo
         */
      //  VolatileUtils.doFrist();

    }
   //备忘录模式
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
