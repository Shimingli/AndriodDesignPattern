package com.shiming.andrioddesignpattern.memento_model;

/**
 * @author shiming
 * @version v1.0 create at 2017/10/13
 * @des originator 负责创建一个备忘录，恢复自身内部的状态，同时决定那么的状态可以去备份
 */
public class OriginatorPlayerTwo {
    private int lv = 1;
    private int hp = 100;
    private int mp = 50;

    public void play(){
        lv++;
        hp+=100;
        mp+=50;
        System.out.println("升级了，当前级别"+lv+" hp:"+hp+" mp:"+mp);
    }

    public void attackBoss(){
        hp-=80;
        mp-=40;
        System.out.println("打boss之后，当前级别"+lv+" hp:"+hp+" mp:"+mp);
    }

    public MementoTwo createMemoto(){
        MementoTwo memoto = new MementoTwo();
        memoto.lv = lv;
        memoto.hp = hp;
        memoto.mp = mp;
        return memoto;
    }

    public void restore(MementoTwo memoto){
        lv = memoto.lv;
        hp = memoto.hp;
        mp = memoto.mp;
        System.out.println("回档了，当前级别"+lv+" hp:"+hp+" mp:"+mp);
    }

    @Override
    public String toString() {
        return   "当前状态：级别"+lv+" hp:"+hp+" mp:"+mp;
    }
}
