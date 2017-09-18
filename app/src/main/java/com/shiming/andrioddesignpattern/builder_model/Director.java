package com.shiming.andrioddesignpattern.builder_model;

/**
 * Created by shiming on 2017/9/10.
 * 导演者：Director，就是操作Builder对象的
 * 担任这个角色的类调用具体建造者角色以创建产品的对象，应当指出的是：导演者
 * 并没有产品类的具体信息，真正拥有产品类的具体知识的是具体建造者的角色
 */

public class Director {
    private Builder mBuilder;
    public Director(Builder builder){
        mBuilder=builder;
    }
    public void construct(String name, boolean sex, int age, int height, int weight) {
        mBuilder.setName(name);
        mBuilder.setSex(sex);
        mBuilder.setAge(age);
        mBuilder.setHeight(height);
        mBuilder.setWeight(weight);
    }

}
