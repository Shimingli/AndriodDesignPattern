package com.shiming.andrioddesignpattern.flyweight_model;

import java.util.Random;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/21
 * @des 火车票实车票，具体的车票
 */
public class TrainTicket implements Ticket {
    private String mFrom;
    private String mTo;
    private String mString;

    public TrainTicket(String from, String to) {
        mFrom = from;
        mTo = to;
    }

    @Override
    public int getTicketMenoy() {
        //产生5000类的随机的价格
        return new Random().nextInt(5000);
    }

    @Override
    public String setTicketType(String des) {
        return "我是哪里来的---->"+mString+"<---从哪里："+mFrom+"到哪里："+mTo+"价格："+getTicketMenoy()+"什么类型的车票："+des;
    }

    @Override
    public void setWhere(String string) {
        mString = string;

    }
}
