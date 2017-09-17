package com.shiming.andrioddesignpattern.facade_model;

import android.util.Log;

/**
 * Created by shiming on 2017/9/17.
 */

public class HandImpl implements Hand {
    @Override
    public void takePhone() {
        Log.d("HandImpl","takePhone");
    }

    @Override
    public void closePhone() {
       Log.d("HandImpl","closePhone");
    }
}
