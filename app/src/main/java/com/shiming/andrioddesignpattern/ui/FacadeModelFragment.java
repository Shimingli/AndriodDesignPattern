package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.facade_model.Facade;

/**
 * Created by shiming on 2017/9/17.
 * 外观模式：是一个高频使用的模式，精髓在与封装，让客户端依赖最少的类，直接依赖外观类
 * 而不是依赖所有的子系统类
 *
 * 优点： 对客户程序隐藏了子系统的细节，因而减少了客户对子系统的耦合，
 * 外观类对子系统的接口封装，是的系统更加易于使用
 * 合理的使用facade，可以帮助我们更好的划分访问的层次，有些方法对系统外的，有些方法是
 * 系统内部使用，把需要暴露的给外部调用集中到外观类，这样既方便客户端的使用，也很好的隐藏了内部的细节
 * 缺点：
 * 外观类接口膨胀，由于子系统的接口都由外观类统一对外暴露，是的外观类上的api接口过多
 * 增加了用户的使用成本
 * 当业务逻辑变更的时候，可能需要直接修改外观类
  使用的场景
  在以下情况下可以考虑使用外观模式：
 (1)设计初期阶段，应该有意识的将不同层分离，层与层之间建立外观模式。
 (2) 开发阶段，子系统越来越复杂，增加外观模式提供一个简单的调用接口。
 (3) 维护一个大型遗留系统的时候，可能这个系统已经非常难以维护和扩展，但又包含非常重要的功能，为其开发一个外观类，以便新系统与其交互。
 *
 */

public class FacadeModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.facade_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        04-04 09:21:50.503 5305-5305/com.shiming.andrioddesignpattern D/PhoneImpl: dail
//        04-04 09:21:50.503 5305-5305/com.shiming.andrioddesignpattern D/HandImpl: takePhone
//        04-04 09:21:50.503 5305-5305/com.shiming.andrioddesignpattern D/PhoneImpl: hangUp
//        04-04 09:21:50.503 5305-5305/com.shiming.andrioddesignpattern D/HandImpl: closePhone
        Facade facade = new Facade();
        facade.doFirst();
        facade.close();

    }
}
