package com.shiming.andrioddesignpattern.template_model;

import android.content.Context;

import com.shiming.andrioddesignpattern.utils.ToastUtil;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/22
 * @des
 */
public class ConcreteClass extends AbstractClass {


    @Override
    protected void doFirst(Context context) {
        ToastUtil.showShort(context,"ConcreteClass doFirst");
    }

    @Override
    protected void doSecond(Context context) {
        ToastUtil.showShort(context,"ConcreteClass doSecond");
    }

}
