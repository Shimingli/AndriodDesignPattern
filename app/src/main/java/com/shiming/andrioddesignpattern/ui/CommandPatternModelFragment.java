package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.command_pattern_model.Command;
import com.shiming.andrioddesignpattern.command_pattern_model.ConcreteCommandA;
import com.shiming.andrioddesignpattern.command_pattern_model.ConcreteCommandB;
import com.shiming.andrioddesignpattern.command_pattern_model.Invoke;
import com.shiming.andrioddesignpattern.command_pattern_model.Receiver;

/**
 * @author shiming
 * @version v1.0 create at 2017/10/9
 * @des commandPattern 命令模式：将一个请求封装成为一个对象，从而使你可用不同的请求对客户进行参数化，对请求排队或者是记录请求日志
 * 以及支持可撤销的操作
 * 使用的场景：
 * 1、系统需要将请求调用者和请求接受者解耦，使得调用者和接受者不直接的交互
 * 2、系统需要在不同的时间指定请求，将请求队列（线程池+工作队列）去执行请求
 * 3、系统需要支持命令的撤销（undo）和redo操作，比如系统挂掉之后重启做一些恢复的操作，还有数据库的事务等
 * 命令模式其实就是对命令进行封装，将命令请求者和命令执行者的责任分离开来实现松耦合
 *
 * 总结：
 * 1、每一个命令都是一个操作：请求的一方发出请求，要求执行一个操作；接受的一方收到请求，并执行操作
 * 2、命令模式允许请求的一方和接受的一方独立开来，使得请求的一方不必知道接收请求一方的接口，更不知道请求是怎么样被接收的，以及操作是否被执行
 * 合适被执行，以及怎么样被执行
 * 3、命令模式是请求本身成为一个对象，这个对象和其他对象一样可以被储存和传递，只有实现了抽象命令接口的具体命令才能与接受者相关联
 *  优点：比较容易的设计一个命令队列，在需要的情况下，可以比较轻易的把统计计入到命令中
 *
 *  类间解耦：调用者角色与接收者角色之间没有任何依赖关系，调用者实现功能时只需调用Command 抽象类的execute方法就可以，不需要了解到底是哪个接收者执行。
可扩展性：Command的子类可以非常容易地扩展，而调用者Invoker和高层次的模块Client不产生严 重的代码耦合。
命令模式结合其他模式会更优秀：命令模式可以结合责任链模式，实现命令族解析任务；结合模板方法模式，则可以减少 Command子类的膨胀问题。
 *   缺点：产生很多不必要的类，这样的
 *   */


public class CommandPatternModelFragment extends BaseFragment {

    private TextView mCommandDes;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.command_pattern_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCommandDes = (TextView) getView().findViewById(R.id.commmand_des);
        //创建一个命令接受者
        Receiver receiver = new Receiver();

        //具体的命令者
        Command concreteCommandA = new ConcreteCommandA(receiver);
        Command concreteCommandB = new ConcreteCommandB(receiver);

        //命令的调用者
        Invoke invoke = new Invoke();
        invoke.setCommandA(concreteCommandA);
        invoke.setCommandB(concreteCommandB);

        //发起调用者命名的请求
        String invoke1 = invoke.invoke(0);
        String invoke2 = invoke.invoke(1);

        mCommandDes.setText("我是通过命令模式得到的值：\n"+invoke1+"\n"+invoke2);

        //        PackageManagerService
//        HandlerParams
//                MoveParams
    }

}
