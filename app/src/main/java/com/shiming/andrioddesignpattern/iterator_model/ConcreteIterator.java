package com.shiming.andrioddesignpattern.iterator_model;

/**
 * Created by shiming on 2017/9/24.
 * 具体的迭代子角色类
 */

public class ConcreteIterator implements Iterator {
    //持有被迭代的具体的聚合对象
    private final ConcreteAggregate mAggregate;
    //记录当前聚集对象的大小
    private  int mSize=0;
    //内部索引，记录当前迭代到的索引的位置
    private int index=0;

    public ConcreteIterator(ConcreteAggregate aggregate) {
        mAggregate = aggregate;
        mSize = aggregate.size();
        index=0;
    }


    @Override
    public void first() {
         index=0;
    }

    @Override
    public void next() {
        if (index<mSize){
            index++;
        }

    }

    @Override
    public boolean isDone() {
        return index>=mSize;
    }

    /**
     * 返回当前的元素
     * @return
     */
    @Override
    public Object currentItem() {
        return mAggregate.getElement(index);
    }
}
