package com.shiming.andrioddesignpattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.shiming.andrioddesignpattern.ui.BuilderModelFragment;
import com.shiming.andrioddesignpattern.ui.FactoryMethodModelFragment;
import com.shiming.andrioddesignpattern.ui.ProtoTypeModelFragment;
import com.shiming.andrioddesignpattern.ui.SingletonModelFragment;

import java.util.ArrayList;

/**
 * @author shiming
 * @version v1.0 create at 2017/9/14
 * @des  创建者模式，共五种：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。
 * 这里我把工厂和抽象工厂合并在一起的
 *
 */
public class CreatorModelActivity extends FragmentActivity {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creator_model_activity_layout);

        ViewPager pager= (ViewPager) findViewById(R.id.viewpager);
        SlidingTabLayout tab= (SlidingTabLayout) findViewById(R.id.tap);
        mTitles = new String[]{
                this.getResources().getString(R.string.all_order_tab1),
                this.getResources().getString(R.string.all_order_tab2),
                this.getResources().getString(R.string.all_order_tab3),
                this.getResources().getString(R.string.all_order_tab4),
                };

        mFragments.add(new FactoryMethodModelFragment());
        mFragments.add(new SingletonModelFragment());
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
