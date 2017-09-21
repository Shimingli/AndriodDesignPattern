package com.shiming.andrioddesignpattern.flyweight_model;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/21
 * @des fleweight  定义需要关系的东西
 */
public interface Ticket {
    int getTicketMenoy();
    String setTicketType(String des);

    void setWhere(String string);
}
