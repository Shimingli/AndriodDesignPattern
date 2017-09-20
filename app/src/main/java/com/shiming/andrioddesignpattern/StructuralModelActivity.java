package com.shiming.andrioddesignpattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.shiming.andrioddesignpattern.proxy_model.ProxyModelFragment;
import com.shiming.andrioddesignpattern.ui.AdapterModelFragment;
import com.shiming.andrioddesignpattern.ui.BridgeModelFragment;
import com.shiming.andrioddesignpattern.ui.CompositeModelFragment;
import com.shiming.andrioddesignpattern.ui.DecoratorModelFragment;
import com.shiming.andrioddesignpattern.ui.FacadeModelFragment;

import java.util.ArrayList;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/14
 * @des 结构型模式，共七种：适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。
 */
public class StructuralModelActivity extends FragmentActivity {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;
    private MyPagerAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.structural_model_activity_layout);
        ViewPager pager= (ViewPager) findViewById(R.id.viewpager);
        SlidingTabLayout tab= (SlidingTabLayout) findViewById(R.id.tap);
        mTitles = new String[]{
                this.getResources().getString(R.string.all_order_tab5),
                this.getResources().getString(R.string.all_order_tab10),
                this.getResources().getString(R.string.all_order_tab11),
                this.getResources().getString(R.string.all_order_tab12),
                this.getResources().getString(R.string.all_order_tab13),
                this.getResources().getString(R.string.all_order_tab14),
        };

        mFragments.add(new AdapterModelFragment());
        mFragments.add(new DecoratorModelFragment());
        mFragments.add(new ProxyModelFragment());
        mFragments.add(new FacadeModelFragment());
        mFragments.add(new BridgeModelFragment());
        mFragments.add(new CompositeModelFragment());


        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(mAdapter);
        tab.setViewPager(pager);
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
