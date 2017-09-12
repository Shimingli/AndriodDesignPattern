package com.shiming.andrioddesignpattern.prototype_model;

/**
 * Created by shiming on 2017/9/12.
 * des：在java中，原型类必须实现下面两个条件的其中一个
 * 1、实现Cloneable皆苦，这个接口只有一个作用，就是在运行时候通知虚拟机可以安全的实现
 * 此接口上  clone的方法，在java的虚拟机中，只有实现了这个接口的类才可以被拷贝，否者
 * 会抛出CloneNotSupportedException的
 * 2、重写object类的clone的方法，java中，所有类的父类都是Object类,Object类中有个
 * 方法clone，作用是返回一个拷贝的被吸纳该，但是它的作用域是是protected，只能子类拥有
 * 因此 把clone的方法作用域修改为public
 */

public class ProtoType implements Cloneable  {
    @Override
    public ProtoType clone()  {
        try {
            return (ProtoType)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
