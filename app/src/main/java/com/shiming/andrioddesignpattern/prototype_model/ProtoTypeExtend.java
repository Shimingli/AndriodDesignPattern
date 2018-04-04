package com.shiming.andrioddesignpattern.prototype_model;

/**
 * Created by shiming on 2017/9/12.
 * 原型模式实现的第二种的方法
 */

/**
 * java.lang.CloneNotSupportedException: Class doesn't implement Cloneable
 */
public class ProtoTypeExtend  implements  Cloneable{
    @Override
    public ProtoTypeExtend clone()  {
        try {
            System.out.println("shiming clone");
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
