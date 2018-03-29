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

    /**
     * 同事本身的行为，这种行为叫自发行为 ，selfMethod
     * @param type
     */
    @Override
    public void doSelMethod(String type) {
        System.out.println("ConcreteFly---doSelfMethod");
        System.out.println("Fly:"+ super.mAirName +"---"+type.toLowerCase());
    }

    /**
     * 第二种必须依赖中介者才能完成，叫做依赖行为
     * @param type
     */
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
