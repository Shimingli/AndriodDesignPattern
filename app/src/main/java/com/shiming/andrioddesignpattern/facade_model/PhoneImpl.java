package com.shiming.andrioddesignpattern.facade_model;

import android.util.Log;

/**
 * Created by shiming on 2017/9/17.
 */

public class PhoneImpl implements Phone {
    @Override
    public void dail() {
        Log.d("PhoneImpl","dail");
    }

    @Override
    public void hangUp() {
        Log.d("PhoneImpl","hangUp");
    }
}
