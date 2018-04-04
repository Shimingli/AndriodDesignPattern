package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.flyweight_model.Ticket;
import com.shiming.andrioddesignpattern.flyweight_model.TicketFactory;

/**
 * Created by shiming on 2017/9/20.
 * 享元模式：享元模式是对象池的一种实现的方法，主要目的是用来尽可能的减少内存的使用
 * 适合于存在大量重复对象的场景，来缓存可以共享的对象，避免创建过多的对象的效果，已到达提升性能的作用
 * 可以看做是一个对象池，存放的都是单利的对象，是不重复的，如果我们创建了一个对象，正好对象池里面已经纯在了，
 * 那么直接的复用就可以：
 * 总结就一句话：采用一个共享类避免大量拥有相同的内容的“小类的开销”
 *
 * 享元模式德优缺点：优点在于大幅度的降低内存中对象的数量，但是，它做到这一点代价优点高，享元模式使得系统更加复杂
 * 为了使对象可以共享，需要将一些状态外部化，这使得一些程序逻辑更加的复杂
 * 享元模式将享元对象的状态外部化，而读取外部状态使得运行的时间稍微变长
 *
 *
 * 我们12306的购票的系统，如果每个人买张车票都需要在后台给它new个车票的实体类的话，
 全中国的人民那么都去买，是不是服务器必然挂掉，每次查询一个火车票的结果，那么必然会真加大量的创建的对象，销毁，使得
 GC任务繁重，那么这时候就需要使用享元模式了
 */

public class FlyWeightModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fly_weight_model_fragment,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView) getView().findViewById(R.id.tv_des);

        Ticket ticket01=TicketFactory.getTicket("北京","厦门");//每一个Ticket表示一个用户的请求
        String des1 = ticket01.setTicketType("卧铺");
        Ticket ticket02= TicketFactory.getTicket("北京","厦门");
        String des2= ticket02.setTicketType("硬座");
        Ticket ticket03=TicketFactory.getTicket("北京","厦门");
        String des3 = ticket03.setTicketType("软卧");
        textView.setText(des1+"\n"+des2+"\n"+des3);
        //jvm对象只创建一个String对象对应两个不同的引用，对于多个相同的字符串资源，jvm只会保存一份，这就避免了资源的浪费
        String aa="bb";
        String bb="bb";

    }
}
