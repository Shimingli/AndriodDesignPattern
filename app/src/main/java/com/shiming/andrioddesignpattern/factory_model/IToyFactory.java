package com.shiming.andrioddesignpattern.factory_model;

/**
 * Created by shiming on 2017/9/10.
 * 工厂的接口
 */

public interface IToyFactory {
     <T extends IToy> IToy creator(Class<T> clazz);
}
