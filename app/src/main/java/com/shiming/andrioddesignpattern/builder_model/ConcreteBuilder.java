package com.shiming.andrioddesignpattern.builder_model;

/**
 * Created by shiming on 2017/9/10.
 *concrete:具体的 concreteBuilder：具体建造者：担任这个角色和应用程序紧密关联，
 * 它是应用程序下调用下创建产品的实例。主要的任务包括：1、实现抽象建造者builder所声明的接口
 * 给出完成创建产品实例的操作，在建造过程完成后，提供产品的实例
 */

public class ConcreteBuilder implements Builder{

    private String name;
    private boolean sex;
    private int age;
    private int height;
    private int weight;
    @Override
    public ConcreteBuilder setName(String name) {
        this.name = name;
        return this;
    }
    @Override
    public ConcreteBuilder setSex(boolean sex) {
       this.sex = sex;
        return this;
    }
    @Override
    public ConcreteBuilder setAge(int age) {
        this.age = age;
        return this;
    }
    @Override
    public ConcreteBuilder setHeight(int height) {
        this.height = height;
        return this;
    }
    @Override
    public ConcreteBuilder setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    /**
     * 通过创建人的属性返回一个对象
     * @return 返回的是person
     */
    @Override
    public Person createPerson(){
        return new Person(name,sex,age,height,weight);
    }
}
