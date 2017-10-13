package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.memento_model.CareTaker;
import com.shiming.andrioddesignpattern.memento_model.CareTakerTwo;
import com.shiming.andrioddesignpattern.memento_model.Originator;
import com.shiming.andrioddesignpattern.memento_model.OriginatorPlayerTwo;


/**
 * @author shiming
 * @version v1.0 create at 2017/10/13
 * @des memento 备忘录模式 :是一种行为设计模式，用于保存对象当前的状态，以便之后可以再次恢复到此状态
 * 备忘录模式要保证保存的对象状态不能被对象从外部访问，保护好别保存的这些状态对象的完整性以及内部实现不向对外暴露
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这个以后就可将该对象恢复到原来保存的状态
 * 使用的场景
 * 需要保存一个对象在某个适合的全部或部分的状态时候
 * 一个对象不希望外部直接访问其内部状态时候
 *
 * 优点是
 * 1、提供了一种备份恢复机制，使用户能够方便的回到某个时刻的状态。
 * 2、保存的状态是保护在发起人之外的类的，实现了对保存的状态的封装，发起人就不需要备份处理了
 * 缺点是
 * 1、每一次保存都会消耗一定的内存，当保存的状态非常多的时候，会非常的消耗资源
 *
 *Android中的备忘录模式
 * SavedState 和TimePicker的 关系 ：   protected static class SavedState extends View.BaseSavedState
 * SavedState支持小时和分钟数的存储，这样就可以确保在一些关键节点前后保持状态一直
 * 安卓的横竖屏也有这种的设计模式，捕捉切换前的状态然后切换后的状态保存 这个也是 备忘录的模式
 */
public class MementoModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.memento_model_fragment_layout, null, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Originator o = new Originator();

        CareTaker c = new CareTaker();
        //改变负责人对象的状态
        o.setState("On");
        //创建备忘录对象，并将发起人对象的状态储存起来
        c.setMemento(o.createMemento());
        //修改发起人的状态
        o.setState("Off");
        //恢复发起人对象的状态
        o.recoverMemento(c.getMemento());

        System.out.println(o.getState());
        /**第二种的实现的方式，这里我没搞得太明白  所以这是第二种的输出的结果   **/
        //升级了，当前级别2 hp:200 mp:100
        //存档
        //打boss之后，当前级别2 hp:120 mp:60
        //回档了，当前级别2 hp:200 mp:100
        OriginatorPlayerTwo player = new OriginatorPlayerTwo();
        player.toString();
        player.play();
        player.toString();
        System.out.println("存档");
        CareTakerTwo caretaker = new CareTakerTwo();
        caretaker.save(player.createMemoto());
        player.attackBoss();
        player.toString();
        player.restore(caretaker.load());
        player.toString();

    }
}
