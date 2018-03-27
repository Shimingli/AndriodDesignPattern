package com.shiming.andrioddesignpattern.mediator_Pattern;

import java.util.ArrayList;

/**
 * author： Created by shiming on 2018/3/27 11:33
 * mailbox：lamshiming@sina.com
 */
//飞机调度中介中心
public class AirportMediator implements Mediator{

    //本机场所有的飞机
    private ArrayList<Fly> array = new ArrayList<Fly>();

    public void add(ConcreteFly fly){
        array.add(fly);
    }

    public void remove(ConcreteFly fly){
        array.remove(fly);
    }
    //执行调度命令
    @Override
    public void doManager(Fly fly,String type){
        System.out.println("AirportMediator---doManager");

        if(type.equals(Fly.TYPE_IN)){
            if(!array.contains(fly)){
                array.add(fly);
            }
        }else if (type.equals(Fly.TYPE_OUT)) {
            if(array.contains(fly)){
                array.remove(fly);
            }
        }

        for(int i=0;i<array.size();i++){
            ((ConcreteFly)array.get(i)).listerMediatorNotification("Mediator Notifycation:Fly:"+fly.getName()
                    +"---"+type.toLowerCase()+"---各个飞机按命令调度");
        }

    }

}
