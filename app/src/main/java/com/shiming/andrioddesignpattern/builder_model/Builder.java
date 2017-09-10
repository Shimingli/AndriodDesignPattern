package com.shiming.andrioddesignpattern.builder_model;

/**
 * Created by shiming on 2017/9/10.
 * 给出一个抽象接口，以规范产品对象的各个组成成分的建造，产品所包含的零件的数目和建造
 * 方法的数目想符合，如果说零件越多，就有很多相对的建造的方法
 */

public interface Builder {
    Builder setName(String name);
    Builder setSex(boolean sex);
    Builder setAge(int age);
    Builder setHeight(int height);
    Builder setWeight(int weight);
    Person createPerson();
}
