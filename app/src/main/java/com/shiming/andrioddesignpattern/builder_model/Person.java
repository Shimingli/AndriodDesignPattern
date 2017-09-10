package com.shiming.andrioddesignpattern.builder_model;

/**
 * Created by shiming on 2017/9/10.
 * 产品（Product）:产品就是这个Person类，建造中较为复杂的对象，
 * 一般来说，一个系统会有很多的产品，而且这些产品类，不一定会有共同的接口，可以是互相不关联的对象
 *
 */

public class Person {
    //一个人的属性
    private String name;
    private boolean sex;
    private int age;
    private int height;
    private int weight;

    public Person(String name, boolean sex, int age, int height, int weight) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
