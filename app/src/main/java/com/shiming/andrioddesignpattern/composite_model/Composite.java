package com.shiming.andrioddesignpattern.composite_model;

import java.util.ArrayList;

/**
 * Created by shiming on 2017/9/19.
 * d=Composite 定义有子节点的行为，用来储存部件，实现在Component接口中对子部件有关的操作
 */

public class Composite extends Component{
    //一个子对象集合，用来存储其下属的枝节点和叶节点
    private ArrayList<Component> children=new ArrayList<>();
    public Composite(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        children.add(component);
    }

    @Override
    public void remove(Component component) {
        children.remove(component);
    }

    @Override
    public void disPlay(int level) {
        System.out.print("Leaf disPlay"+level);
        //遍历节点，并且显示
        for (int i=0;i<children.size();i++){
            Component component = children.get(i);
            component.disPlay(level+i);
        }
    }
}
