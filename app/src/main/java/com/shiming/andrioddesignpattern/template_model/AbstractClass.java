package com.shiming.andrioddesignpattern.template_model;

import android.content.Context;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/22
 * @des
 */
public abstract class AbstractClass {
    protected abstract void doFirst(Context context);
    protected abstract void doSecond(Context context);

    /**
     * 子类可以更具实际情况重写这个方法
     * @return
     */
    protected boolean canDoSecond(){
        return true;
    }

    /**
     * 用final修改保证之类不能去修改源代码
     * @param context
     */
    public final void templateMethod(Context context){
        /*
         *调用方法，完成相关的方法的逻辑
         */
        doFirst(context);
        if (canDoSecond()){
            doSecond(context);
        }
    }

}
