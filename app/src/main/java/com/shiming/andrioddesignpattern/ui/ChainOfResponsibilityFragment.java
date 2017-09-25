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
