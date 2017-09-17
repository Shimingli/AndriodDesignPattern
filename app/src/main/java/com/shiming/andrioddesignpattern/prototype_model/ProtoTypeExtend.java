package com.shiming.andrioddesignpattern.prototype_model;

/**
 * Created by shiming on 2017/9/12.
 * 原型模式实现的第二种的方法
 */

public class ProtoTypeExtend  {
    @Override
    public ProtoTypeExtend clone()  {
        try {
            return (ProtoTypeExtend) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getClone(){
        return "我是本尊";
    }

    public ProtoTypeExtend cloneDD()  {
        try {
            return (ProtoTypeExtend) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void test(){

    }

}
