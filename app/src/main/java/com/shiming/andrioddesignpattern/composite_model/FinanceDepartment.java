package com.shiming.andrioddesignpattern.composite_model;

/**
 * Created by shiming on 2017/9/20.
   financedepartment 财务部属于公司的
 */

public class FinanceDepartment extends Company{
    public FinanceDepartment() {
    }

    public FinanceDepartment(String name) {
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
