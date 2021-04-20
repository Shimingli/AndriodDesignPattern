package com.shiming.andrioddesignpattern.strategy_model;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.strategy_model.CalPriceFactory;
import com.shiming.andrioddesignpattern.strategy_model.ContextPrice;
import com.shiming.andrioddesignpattern.strategy_model.FivePriceStrategy;
import com.shiming.andrioddesignpattern.strategy_model.SevenPriceStrategy;
import com.shiming.andrioddesignpattern.ui.BaseFragment;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/21
 * @des  strategy 策略模式 ：在软件开发中实现一个功能有多重的算法，我们可以根据不同的环境或者是
 * 条件不同算法不同或者是策略来完成该功能，
 * 策略模式定义：定义一系类的算法，把每一个算法封装起来，并且使他们可以互相的替换，本模式使得算法可独立于使用它的
 * 客服端而变化
 * 在安卓中，listview就是实现的策略的模式
 * listView.setAdapter(ListAdapter) 当实现复杂的条目 设置的是BaseAdapter 或者是ArrayAdapter 是listAdapter
 * 的子类，通过对比发现listAdapter就是策略strategy接口，arrayadapter就是具体的实现的子类
 *
 * 策略模式使用的场景：在某一场景需要更多的情况，不同情况下需要处理大量的if-else 或者是switch 但是大致的
 * 功能是一样的，这时候我们就可以考虑使用策略模式
 * 优点：每个算法都独立，方便测试
 * 结构清晰
 * 客户端引用的是接口，耦合度更低，扩展性更强
 * 缺点：随着策略增加，子类也会增多
 *
 *
 */
public class StrategyModelFragment extends BaseFragment {

    private TextView mTextView;
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.strategy_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FivePriceStrategy fivePriceStrategy = new FivePriceStrategy();
        ContextPrice contextPrice = new ContextPrice(fivePriceStrategy);
        int i = contextPrice.setNewPrice(10);
        SevenPriceStrategy sevenPriceStrategy = new SevenPriceStrategy();
        contextPrice.setNewStrategy(sevenPriceStrategy);
        int i1 = contextPrice.setNewPrice(20);

        mTextView = (TextView) getView().findViewById(R.id.tvdes);
        //
        final TextView viewById1 = (TextView) getView().findViewById(R.id.tv_text);
        mTextView.setText("第一种策略模式实现的价格:\n原价是10 减半后"+i+"\n"+"第二种策略模式实现的价格\n原价是20 7折后"+i1);

        //时间差值器，也是策略模式
        final Button viewById = (Button) getView().findViewById(R.id.btn);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewById1, View.ALPHA, 0, 1);
                //这里就相当于策略模式实现的
                objectAnimator.setInterpolator(new AccelerateInterpolator());//加速
                objectAnimator.setInterpolator(new OvershootInterpolator());//huilai
                objectAnimator.start();
            }
        });


        /*  我觉得策略模式 startegy在这里还要扩展一下下    **/
        Player player = new Player();
        player.buy(5000D);
//        CalPriceFactory.getInstance().createCalPrice(player);
//        System.out.println("玩家需要付钱：" + player.calLastAmount());
//        player.buy(12000D);
//        System.out.println("玩家需要付钱：" + player.calLastAmount());
//        player.buy(12000D);
//        System.out.println("玩家需要付钱：" + player.calLastAmount());
//        player.buy(12000D);
//        System.out.println("玩家需要付钱：" + player.calLastAmount());
    }
}
