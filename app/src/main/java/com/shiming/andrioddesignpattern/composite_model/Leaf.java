package com.shiming.andrioddesignpattern.composite_model;

/**
 * Created by shiming on 2017/9/19.
 *叶子(Leaf) 在组合中表示节点对象，叶子节点不能有子节点
 */

public class Leaf extends Component {
    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        System.out.println("Leaf add"+mName);
    }

    @Override
    public void remove(Component component) {
        System.out.println("Leaf remove"+mName);
    }

    @Override
    public void disPlay(int level) {
        System.out.println("Leaf disPlay "+level+mName+"==mName");
    }
}
