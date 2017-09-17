package com.shiming.andrioddesignpattern.proxy_model;

import android.util.Log;

/**
 * Created by shiming on 2017/9/17.
 * 委托类：真实的实现了request(
 */

public class StaticRealSubject implements StaticSubject {
    @Override
    public void request() {
        Log.d("StaticRealSubject","request");
    }
}
