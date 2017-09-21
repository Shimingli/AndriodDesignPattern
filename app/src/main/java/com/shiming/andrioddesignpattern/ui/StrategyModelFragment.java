package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/21
 * @des  strategy 策略模式
 */
public class StrategyModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.strategy_model_fragment_layout,null,false);
    }
}
