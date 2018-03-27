package com.shiming.andrioddesignpattern.mediator_Pattern;

/**
 * author： Created by shiming on 2018/3/27 11:46
 * mailbox：lamshiming@sina.com
 * 具体飞机类
 */

public class ConcreteFly extends Fly {
    public ConcreteFly(String airName, String airId) {
        super(airName, airId);
    }

    @Override
    public void doSelMethod(String type) {
        System.out.println("ConcreteFly---doSelfMethod");
        System.out.println("Fly:"+ super.mAirName +"---"+type.toLowerCase());
    }

    @Override
    public void doDepMethod(String type) {
        System.out.println("ConcreteFly---doDepMethod");
        System.out.println("Fly:"+ super.mAirName +"-------doDepMethod()");
        super.getAirportMediator().doManager(this,type);

    }

    @Override
    public void in() {
        doSelMethod(TYPE_IN);
        doDepMethod(TYPE_IN);
    }

    @Override
    public void out() {
        System.out.println("ConcreteFly---out()---Fly:"+ super.mAirName +"-------out");
        doSelMethod(TYPE_OUT);
        doDepMethod(TYPE_OUT);
    }


    @Override
    public void listerMediatorNotification(String notification) {
        System.out.println("Fly:"+super.mAirName + "--lister Mediator Notification:"+notification);
    }
}
