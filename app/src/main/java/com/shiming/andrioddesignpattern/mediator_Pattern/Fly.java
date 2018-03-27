package com.shiming.andrioddesignpattern.mediator_Pattern;

/**
 * author： Created by shiming on 2018/3/27 11:32
 * mailbox：lamshiming@sina.com
 */
//抽象类飞机类
public  abstract class Fly {
    //飞机 出站还是进站
    public static String TYPE_IN="in";
    public static String TYPE_OUT="out";
    //调度中心
    protected AirportMediator mAirportMediator;

    protected String mAirName;
    protected String mAirId;

    public Fly(String airName, String airId) {
        mAirName = airName;
        mAirId = airId;
    }

     public AirportMediator getAirportMediator(){
        return mAirportMediator;
     }
     public void setAirportMediator(AirportMediator mediator){
         mAirportMediator=mediator;
     }
     //定义飞机执行自己的操作
    public  abstract void doSelMethod(String type);
     //定义飞机执行与机场调度中心的操作
    public abstract void doDepMethod(String type);

    public abstract void  in();
    public abstract void  out();

    //定义飞机监听机场调度中心的通知
    public abstract void listerMediatorNotification(String notification);

    public String getName() {
        return mAirName;
    }
}
