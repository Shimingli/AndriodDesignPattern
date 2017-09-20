package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.composite_model.Component;
import com.shiming.andrioddesignpattern.composite_model.Composite;
import com.shiming.andrioddesignpattern.composite_model.Leaf;

/**
 * Created by shiming on 2017/9/19.
 * 组合模式
 */

public class CompositeModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.composite_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //树根，生成两个叶子节点
        Composite composite = new Composite("Root");
        composite.add(new Leaf("Leaf A "));
        composite.add(new Leaf("Leaf B "));
         //为树根增加两个枝节点
        Composite compositeX = new Composite("Branch X");
        Composite compositeY = new Composite("Branch Y");
        composite.add(compositeX);
        composite.add(compositeY);

        //为BranchX增加叶节点
        compositeX.add(new Leaf("Leaf A In Branch X"));

        // 为BranchX增加枝节点
        Component branchZ = new Composite("Branch Z in Branch X");
        compositeX.add(branchZ);

        // 为BranchY增加叶节点
        compositeY.add(new Leaf("Leaf in Branch Y"));

        // 为BranchZ增加叶节点
        branchZ.add(new Leaf("Leaf in Branch Z"));
        composite.disPlay(1);

    }
}
