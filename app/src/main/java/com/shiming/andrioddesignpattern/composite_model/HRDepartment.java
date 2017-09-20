package com.shiming.andrioddesignpattern.composite_model;

/**
 * Created by shiming on 2017/9/20.
 * hr 人力资源部
 */

public class HRDepartment extends Company {
    public HRDepartment() {
    }

    public HRDepartment(String name) {
        super(name);
    }

    @Override
    public void add(Company company) {

    }

    @Override
    public void romove(Company company) {

    }

    @Override
    public void display(int depth) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }
        System.out.println(new String(sb) + this.getName() ) ;
    }
}
