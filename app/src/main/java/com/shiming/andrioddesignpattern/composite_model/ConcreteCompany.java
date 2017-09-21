package com.shiming.andrioddesignpattern.composite_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiming on 2017/9/20.
 *
 * 具体的公司，说不定有很多的子公司
 */

public class ConcreteCompany extends Company {

    private List<Company> cList;

    public ConcreteCompany() {
        cList = new ArrayList<Company>();
    }

    public ConcreteCompany(String name) {
        super(name);
        cList = new ArrayList<Company>() ;
    }

    @Override
    public void add(Company company) {
        cList.add(company);
    }

    @Override
    public void display(int depth) {

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }
        System.out.println(new String(sb) + this.getName());
        for (Company c : cList) {
            c.display(depth + 2);
        }
    }

    @Override
    public void romove(Company company) {
        cList.remove(company);
    }
}
