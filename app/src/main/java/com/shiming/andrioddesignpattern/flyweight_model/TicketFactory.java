package com.shiming.andrioddesignpattern.flyweight_model;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/21
 * @des 火车票的工具类
 */
public class TicketFactory {
    //ConcurrentHashMap 是一个线程安全的map集合，里面引用了分段锁的概念，相对于吧一个大的map拆分很多小的线程安全的
    //hashTable.
    private static ConcurrentHashMap<String,Ticket> sHashMap=new ConcurrentHashMap<>();
    public static Ticket getTicket(String from,String to){
        String key=from+"-"+to;
        if (sHashMap.containsKey(key)){
            sHashMap.get(key).setWhere("我在集合中哦");
            return sHashMap.get(key);
        }else {
            TrainTicket trainTicket = new TrainTicket(from, to);
            trainTicket.setWhere("我在new出来的哦");
            sHashMap.put(key,trainTicket);
            return trainTicket;
        }
    }

}
