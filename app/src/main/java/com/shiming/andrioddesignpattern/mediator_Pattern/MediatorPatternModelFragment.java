package com.shiming.andrioddesignpattern.mediator_Pattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.ui.BaseFragment;

/**
 * author： Created by shiming on 2018/3/27 11:25
 * mailbox：lamshiming@sina.com
 * 中介者模式：用一个中介对象封装一系列的对象交互，中介者使各对象不需要显示地相互作用，从而使其
 * 耦合松散，而且可以独立的改变他们之间的交互
 * 使用的场景：用于多个对象紧密耦合的情况，紧密耦合的标准是：在类中出现了蜘蛛网状结构，所有类都和中心
 * 交流，
 * 实际使用
 * MVC   机场调度中心  中介服务，租房等工作
 *优点：
 * 1、中介者模式的优点就是减少类之间的依赖，把所有的一对多的依赖变成一个一对一的依赖，同时类中只依赖
 * 中介者，减少了类的依赖，当然也降低了类间的耦合
 * 2、提供系统的灵活性，使得各个同时对象独立而且易于复用
 * 缺点：
 * 1、中介者模式中，中介者角色承担了较多的职责，所以一旦这个中介者对象出现了问题，整个系统将会受到重大的影响
 * 2、新增加一个同事类时候，不得不去修改中介者类中或者是具体类中，此时可以使用观察者模式和状态模式来解决这个问题
 *
 * 使用场景
 *  1、一组定义良好的对象，要进行复杂的通信
 * 2、想通过一个中间类来封装多个类中的行为，而又不想 生成太多的之类了
 *
 */

public class MediatorPatternModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mediator_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //飞机调度中心
        AirportMediator airportMediator = new AirportMediator();

        //飞机
        ConcreteFly 四川航空 = new ConcreteFly("四川航空", "001");
        ConcreteFly 东方航空 = new ConcreteFly("东方航空", "002");
        ConcreteFly 南方航空 = new ConcreteFly("南方航空", "003");
        ConcreteFly 厦门航空 = new ConcreteFly("厦门航空", "004");
        //给每个飞机设置 航空控制中心
        四川航空.setAirportMediator(airportMediator);
        东方航空.setAirportMediator(airportMediator);
        南方航空.setAirportMediator(airportMediator);
        厦门航空.setAirportMediator(airportMediator);


        四川航空.in();
        四川航空.out();
        东方航空.in();
        南方航空.in();
        厦门航空.in();
        厦门航空.out();

    }
}
