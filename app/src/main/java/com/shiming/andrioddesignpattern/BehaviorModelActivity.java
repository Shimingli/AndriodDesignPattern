package com.shiming.andrioddesignpattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.SlidingTabLayout;
import com.shiming.andrioddesignpattern.ui.BuilderModelFragment;
import com.shiming.andrioddesignpattern.ui.ProtoTypeModelFragment;
import com.shiming.andrioddesignpattern.ui.StrategyModelFragment;
import com.shiming.andrioddesignpattern.ui.TemplateModelFragment;

import java.util.ArrayList;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/21
 * @des 行为型模式，共十一种：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、
命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。
 */
public class BehaviorModelActivity extends AppCompatActivity {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;
    private MyPagerAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.behavioral_model_activity_layout);

        ViewPager pager= (ViewPager) findViewById(R.id.viewpager);
        SlidingTabLayout tab= (SlidingTabLayout) findViewById(R.id.tap);
        mTitles = new String[]{
                this.getResources().getString(R.string.all_order_tab16),
                this.getResources().getString(R.string.all_order_tab17),
                this.getResources().getString(R.string.all_order_tab3),
                this.getResources().getString(R.string.all_order_tab4),
        };

        mFragments.add(new StrategyModelFragment());
        mFragments.add(new TemplateModelFragment());
        mFragments.add(new BuilderModelFragment());
        mFragments.add(new ProtoTypeModelFragment());


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
