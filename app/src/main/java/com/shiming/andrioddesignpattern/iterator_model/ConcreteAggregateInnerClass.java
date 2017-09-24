package com.shiming.andrioddesignpattern.iterator_model;

import java.util.ConcurrentModificationException;

/**
 * Created by shiming on 2017/9/24.
 * 用内部类实现的方法
 * 动态迭代子与静态迭代子完全相反，在迭代子被产生之后，迭代子保持着对聚集元素的引用，因此任何对原聚集内筒
 * 的修改都会在迭代子对象上反映出来，完整的动态迭代子不好实现，但是简单的动态子很好实现，这里我得到一个新的概念
 * Fail fast
 * fail-fast 机制是java集合(Collection)中的一种错误机制。当多个线程对同一个集合的内容进行操作时，就可能会产生fail-fast事件。例如：当某一个线程A通过iterator去遍历某集合的过程中，若该集合的内容被其他线程所改变了；那么线程A访问集合时，就会抛出ConcurrentModificationException异常，产生fail-fast事件。
 要了解fail-fast机制，我们首先要对ConcurrentModificationException 异常有所了解。当方法检测到对象的并发修改，但不允许这种修改时就抛出该异常。同时需要注意的是，该异常不会始终指出对象已经由不同线程并发修改，如果单线程违反了规则，同样也有可能会抛出改异常。
 诚然，迭代器的快速失败行为无法得到保证，它不能保证一定会出现该错误，但是快速失败操作会尽最大努力抛出ConcurrentModificationException异常，所以因此，为提高此类操作的正确性而编写一个依赖于此异常的程序是错误的做法，正确做法是：ConcurrentModificationException 应该仅用于检测 bug
 仅限于理解，在Java中JAVA语言以接口java.util.Iterator的方式支持迭代子模式，
 Collection接口要求提供iterator()方法，此方法在调用时返还一个Iterator类型的对象。
 而作为Collection接口的子类型，AbstractList类的内部成员类Itr便是实现Iterator接口的类。

 在itr源码中可以看到，方法checkForComodification 会检查聚集的内筒是否刚刚被外界修改过，不是通过迭代子提供的方法修改的
 如果在迭代开始了，聚集的内筒被外界绕过子对象而直接修改的话，这个方法马上会抛出concurretModificatonException的异常
 */

public class ConcreteAggregateInnerClass extends Aggregate {

    private final Object[] mObjects;

    public ConcreteAggregateInnerClass(Object[] objects) {
        mObjects = objects;
    }

    @Override
    Iterator createIterator() {
        return new ConcreteIterator();
    }

    private class ConcreteIterator implements Iterator{
         private  int index=0;
        @Override
        public void first() {
            index=0;
        }

        @Override
        public void next() {
            if (index<mObjects.length){
                index++;
            }
        }

        @Override
        public boolean isDone() {
            return index>=mObjects.length;
        }

        @Override
        public Object currentItem() {
            return mObjects[index];
        }
        private void checkForComodification() {
                throw new ConcurrentModificationException();
        }
    }
}
