package com.shiming.andrioddesignpattern.factory_model;

/**
 * Created by shiming on 2017/9/10.
 */

public class ToyFactory implements IToyFactory {
    @Override
    public <T extends IToy> IToy creator(Class<T> clazz) {
        IToy t = null;
        if (null==clazz){
            throw new IllegalArgumentException("class must no can null");
        }else {

            try {
               t= clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return t;
    }
}
