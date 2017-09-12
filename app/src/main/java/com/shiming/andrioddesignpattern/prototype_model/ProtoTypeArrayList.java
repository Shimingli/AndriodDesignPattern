package com.shiming.andrioddesignpattern.prototype_model;

import java.util.ArrayList;

/**
 * Created by shiming on 2017/9/12.
 * 这个类，主要是区分一下深拷贝，和浅拷贝的问题
 * 深拷贝：就是需要拷贝的类中，所有的东西，比如说：原型类中的数组，容器，饮用对象等
 * 浅拷贝：就是只拷贝基本东西，容器这些不拷贝
 *
 * 会发生深入拷贝有 java中的8中基本类型，和它封装类型，还有string类型 ，其余都是浅拷贝
 * 补充下:基本数据类型：数值型：整数型 byte short int long  浮点类型 float double
 * 字符型 char  布尔型 boolean
 *
 * 引用数据类型 ： f类 class  接口 interface  数组  容器
 */

public class ProtoTypeArrayList implements Cloneable {

    //由于ArrryList不是基本类型,所以成员变量mArrayList是不会拷贝的，幸运的是
    //java中大部分都容器都实现了cloneable这个接口，所以在程度上去实现深入拷贝不太难
    private ArrayList mArrayList=new ArrayList();
    @Override
    protected Object clone()  {
        ProtoTypeArrayList protoTypeArrayList=null;
        try {
            protoTypeArrayList= (ProtoTypeArrayList) super.clone();
            //这里实现了深拷贝，必须这样做
            protoTypeArrayList.mArrayList= (ArrayList) this.mArrayList.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return protoTypeArrayList;
    }
}
