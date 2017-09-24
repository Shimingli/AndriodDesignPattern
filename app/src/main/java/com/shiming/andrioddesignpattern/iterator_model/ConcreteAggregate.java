package com.shiming.andrioddesignpattern.iterator_model;

/**
 * Created by shiming on 2017/9/24.
 * 具体聚集角色类，实现了抽象聚集角色类所要求的接口，也就是createIterator方法，还想外界
 * 提供聚集的元素 getElement（） 而方法size（）向外界提供聚集的大小
 *
 * 这是静态迭代子：静态迭代子由聚集对象创建，并持有聚集对象的一份快照，在产生后这个快照就不再改变。客户端可以继续修改
 * 源集合的内筒，但是迭代子不会反映出聚集的变化。静态迭代子的好处是它的安全性和简易性，换言之，静态迭代子易于实现，不容易出现错误
 * 但是由于静态迭代子将原来聚集赋值了一份，就是那个集合传入ConcreteIterator这个类中，所以在时间和内存资源上有多的消耗
 *
 */

public class ConcreteAggregate extends Aggregate {

    private final Object[] mObjects;

    /**
     * 构造方法，传入聚合对象的具体内筒
     * @param objects
     */
    public ConcreteAggregate(Object[] objects) {
        mObjects = objects;
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    /**
     * 取值方法，向外界提供聚集的元素
     * @param index
     * @return
     */
    public Object getElement(int index){
        if (index<mObjects.length){
            return mObjects[index];
        }else {
            return null;
        }
    }

    /**
     * 向外界提供聚集的大小
     * @return
     */
    public int size(){
        return mObjects.length;
    }

}
