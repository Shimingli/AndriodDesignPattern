package com.shiming.andrioddesignpattern.factory_model;

import com.shiming.andrioddesignpattern.utils.DebugLog;

/**
 * Created by shiming on 2017/9/10.
 */

public class ChildrenToy implements IToy {
    @Override
    public String getToyName() {
        return "名称：孩子的玩具";
    }

    @Override
    public String getToyPrice() {
        return "价格：2 price";
    }

    @Override
    public void doWhat() {
        DebugLog.d("children","play");
    }
}
