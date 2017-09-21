package com.shiming.andrioddesignpattern.composite_model;

/**
 * Created by shiming on 2017/9/20.
 * 总公司：一个component
 */

public abstract class Company {

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
    //总公司的名称
    private  String mName;

    public Company() {
    }

    public Company(String name) {
        mName = name;
    }
    public abstract void add(Company company);

    public abstract void romove(Company company);

    public abstract void display(int depth);
}
