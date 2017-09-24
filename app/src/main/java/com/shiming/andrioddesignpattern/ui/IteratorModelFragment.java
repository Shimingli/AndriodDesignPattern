package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.iterator_model.ConcreteAggregate;
import com.shiming.andrioddesignpattern.iterator_model.Iterator;
import com.shiming.andrioddesignpattern.utils.ToastUtil;

/**
 * Created by shiming on 2017/9/24.
 * Iterator 迭代子模式：迭代子模式又叫做游标模式（Cursor）模式，是对象的行为模式，迭代子模式可以顺序的访问一个聚集中
 * 的元素而不用暴露聚集的内部表象
 *
 * 我理解的迭代子模式：
 * 迭代子模式简化了聚集的接口，迭代具备了一个变量的接口，这样的聚集的接口就不需要具备遍历接口了
 * 每一个聚集对象都可以有一个或者是多个迭代子的对象，每一个迭代子的迭代状态可以是彼此独立的，因此，一个迭代对象可以同时
 * 有几个迭代进行中，由于遍历算法被封装在迭代子的角色里面，因此迭代的算法可以独立于聚集的角色变换
 * 缺点：
 * 如果用静态迭代子，时间和内存是个问题，如果项目过大，迭代子复杂，静态的迭代子不是用
 * 如果是动态迭代子的，可以在时间和性能上有很好的提升，但是一般人，比如像我，能力不够，驾驭不了，特别在多线程中使用中
 * 更加麻烦，所以用到了还需要好好研究
 */

public class IteratorModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.iterator_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建一个聚集类的实例，然后调用聚集对象的工厂createIterator以得到一个迭代子对象，得到迭代子对象
        //客户端开始迭代过程，打印出所有的聚集元素
        Object[] objArray = {"One","Two","Three","Four","Five","Six"};
        //创建聚合对象
        ConcreteAggregate concreteAggregate = new ConcreteAggregate(objArray);
        final Iterator iterator = concreteAggregate.createIterator();
        getView().findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (!iterator.isDone()){
//                    09-24 21:27:38.381 16437-16437/? I/System.out: shiming==One
//                    09-24 21:27:38.422 16437-16437/? I/System.out: shiming==Two
//                    09-24 21:27:38.439 16437-16437/? I/System.out: shiming==Three
//                    09-24 21:27:38.455 16437-16437/? I/System.out: shiming==Four
//                    09-24 21:27:38.468 16437-16437/? I/System.out: shiming==Five
//                    09-24 21:27:38.479 16437-16437/? I/System.out: shiming==Six
                    System.out.println("shiming=="+iterator.currentItem());
                    ToastUtil.showShort(getContext()," shiming==One-->"+"shiming==Two-->"+" shiming==Three-->"+"shiming==Four--->"+" shiming==Five-->"+"shiming==Six");

                    //toast 弹的太快了 ，看不到效果哟
//                    Toast.makeText(getContext(),iterator.currentItem()+"",Toast.LENGTH_SHORT).show();
//                    ToastUtil.showLong(getContext(),iterator.currentItem()+"");
                    iterator.next();
                }
                if (iterator.isDone()){
                    ToastUtil.showShort(getContext(),"我已经迭代完了哦，点我没有用了");
                }

            }
        });

    }
}
