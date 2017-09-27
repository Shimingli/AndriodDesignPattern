package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.chain_on_responsibility_model.DepartmentManager;
import com.shiming.andrioddesignpattern.chain_on_responsibility_model.GeneralManager;
import com.shiming.andrioddesignpattern.chain_on_responsibility_model.ProjectManager;

/**
 * Created by shiming on 2017/9/25.
 *
 * Chain of Responsibility 责任链模式
 * 理解：责任链模式是一种对象的行为模式，在责任链模式中，很多对象由每一个对象对其下家的引用而连接起来形成一条链
 * 请求在这个链上传递，直到链上的一个对象决定处理此请求，发出的这个请求的客服端不知道链上的哪一个对象最终处理这个
 * 请求，这使得系统可以在不影响客户端的情况下动态的重新组织和分配责任，
 * 个人的理解就好比你在公司请假，你不知道是谁批准你的请求，但是你能得到一个结果
 * 还有一点：责任链可以是一条直线，一个环链或者是一个树的结构
 * 何为责任链：说白了就是一个事件的传递的链条，一般这个链条上有很多的事件消费者，每个事件的消费者都有机会接受
 * 并消费事件，假如第一个对象可以处理，则处理，后续的就不能处理，不能处理，就传递到下一个
 * 在安卓中，常见的责任链模式就是touch事件的传递
 *
 * 责任链的优点：
 * 1、降低耦合度：该模式使得一个对象无需知道是哪一个对象处理请求的，对象仅需知道该请求
 * 会被正常的处理，接受者和发送者都没有对方的明确的信息，其链中的对象不需要链的结构
 * 2、职责链可简化对象的互相的链接，他们仅仅需要保持一个指向的引用，而不需要保持它所有的后选择的引用
 * ，简单一点就是这个链只需要知道下个链是那个就行了，而且这个链的是有顺序的哦
 * 3、增强了给对象指派职责的灵活性;当对象中分派职责，职责链给你更多的灵活性。你可以通过在运行时刻对该链条
 * 进行动态的增加或者是修改来改变一个请求的那些职责
 * 4、增加新的请求处理类很方便
 *
 * 缺点：
 * 1、不能保证请求一定被接受，一个请求没有明确的接受者，那么就不能保证它一定会被处理，该请求可能一直到链的
 * 末端都得不到处理
 * 2、代码调试不方便
 *
 * 关于纯与不纯的责任链模式
 * 一个具体处理者角色处理只能对请求作出两种行为中的一个：一个是自己处理（承担责任），另一个是把责任推给下家。不允许出现某一个具体处理者对象在承担了一部分责任后又将责任向下传的情况。
 * 请求在责任链中必须被处理，不能出现无果而终的结局。
 * 不纯的职责链模式
 * 在一个纯的职责链模式里面，一个请求必须被某一个处理者对象所接收；在一个不纯的职责链模式里面，
 * 一个请求可以最终不被任何接收端对象所接收。
 * todo 安卓中的广播个人理解应该是可以作为不纯的责任链模式，自定义一个广播，但是没有接受者
 *
 */

public class ChainOfResponsibilityFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chain_of_responsibility_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //部门经理
        DepartmentManager departmentManager = new DepartmentManager();
        //部门负责人
        GeneralManager generalManager = new GeneralManager();
        //项目经理
        ProjectManager projectManager = new ProjectManager();
        //这两个是有顺序的哦
//        departmentManager.setLastHandler(generalManager);
//        generalManager.setLastHandler(projectManager);
        //处理不了就给大的处理，有个以此到大的顺序
        projectManager.setLastHandler(departmentManager);
        departmentManager.setLastHandler(generalManager);

        String shiming = projectManager.handleRequest("shiming", 800);
        String doudou = projectManager.handleRequest("doudou", 800);
        System.out.println("shiming====" +shiming+"---->"+doudou);

        String shiming1 = projectManager.handleRequest("shiming", 1500);
        String doudou1 = projectManager.handleRequest("doudou", 1500);
        System.out.println("shiming====" +shiming1+"---->"+doudou1);

        String shiming2 = projectManager.handleRequest("shiming", 8000);
        String doudou2 = projectManager.handleRequest("doudou", 8000);
        System.out.println("shiming====" +shiming2+"---->"+doudou2);

    }
}
