package com.shiming.andrioddesignpattern.proxy_model;

import android.util.Log;

/**
 * Created by shiming on 2017/9/17.
 */

public class DynamicRealSubject implements DynamicSubject {
    @Override
    public void request() {
        Log.d("DynamicRealSubject","request");
    }
}
