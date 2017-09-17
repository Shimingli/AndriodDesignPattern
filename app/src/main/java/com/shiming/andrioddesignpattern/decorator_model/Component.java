package com.shiming.andrioddesignpattern.decorator_model;

/**
 * Created by shiming on 2017/9/17.
 * component抽象构件角色，真实对象和装饰对象有相同的接口
 * 这样做的结果就能够以与真实对象相同的方法同装饰对象交互。
 *
 */

public interface Component {
    void getModel();
}
