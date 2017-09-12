package com.shiming.andrioddesignpattern;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.SlidingTabLayout;
import com.shiming.andrioddesignpattern.ui.BuilderModelFragment;
import com.shiming.andrioddesignpattern.ui.FactoryMethodModelFragment;
import com.shiming.andrioddesignpattern.ui.ProtoTypeModelFragment;
import com.shiming.andrioddesignpattern.ui.SingletonModelFragment;

import java.util.ArrayList;

/**
创建型模式，共五种：工厂方法模式、抽象工厂模式、单例模式、建造者模式、原型模式。
 结构型模式，共七种：适配器模式、装饰器模式、代理模式、外观模式、桥接模式、组合模式、享元模式。
 行为型模式，共十一种：策略模式、模板方法模式、观察者模式、迭代子模式、责任链模式、
 命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式。
 */
public class MainActivity extends AppCompatActivity {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles;
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager= (ViewPager) findViewById(R.id.viewpager);
        SlidingTabLayout tab= (SlidingTabLayout) findViewById(R.id.tap);
        mTitles = new String[]{
                this.getResources().getString(R.string.all_order_tab1),
                this.getResources().getString(R.string.all_order_tab2),
                this.getResources().getString(R.string.all_order_tab3),
                this.getResources().getString(R.string.all_order_tab4),
                this.getResources().getString(R.string.all_order_tab5),
                this.getResources().getString(R.string.all_order_tab6),
                this.getResources().getString(R.string.all_order_tab7)};

        mFragments.add(new FactoryMethodModelFragment());
        mFragments.add(new SingletonModelFragment());
        mFragments.add(new BuilderModelFragment());
        mFragments.add(new ProtoTypeModelFragment());
        mFragments.add(new FactoryMethodModelFragment());
        mFragments.add(new FactoryMethodModelFragment());
        mFragments.add(new FactoryMethodModelFragment());


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
