package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.composite_model.Company;
import com.shiming.andrioddesignpattern.composite_model.Component;
import com.shiming.andrioddesignpattern.composite_model.Composite;
import com.shiming.andrioddesignpattern.composite_model.ConcreteCompany;
import com.shiming.andrioddesignpattern.composite_model.FinanceDepartment;
import com.shiming.andrioddesignpattern.composite_model.HRDepartment;
import com.shiming.andrioddesignpattern.composite_model.Leaf;

/**
 * Created by shiming on 2017/9/19.
 * 组合模式:叫部分整体模式，它使我们树形结构的问题中，模糊了简单元素和复杂元素的概念
 * 说白了，我他妈现在还有点不明白'
 * 客服端可以处理简单元素一样来处理复杂的元素，从而使得客户端与复杂元素的内部结构解耦。不好理解
 * 电脑的结构：每个文件系统由目录和文件组成，每个目录可以装内容，目录内可以是文件也可以是目录
 * 计算机系统就是递归结构来组织，如果想要这样的数据结构，就可以使用组合模式
 *  组合模式：将对象组合成树形结构以表示“整体部分”的层次结构，组合模式使得用户对单个对象和使用具有一致性
 *  参考：http://blog.csdn.net/qq_25806863/article/details/69568341
 *  view viewgroup 是一种标准的组合模式：在安卓视图中，容器一定是ViewGroup 只有ViewGroup
 *  才能包含其他的view和viewGroup 这是一种安全的模式 ，开发过程中用到的组合模式并不多，
 *  组合模式更多的用于界面ui架构的设计上，而这部分代码通常让开发者去实现的并不多
 *
 *
 *
 */

public class CompositeModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.composite_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //树根，生成两个叶子节点
        Composite composite = new Composite("Root");
        composite.add(new Leaf("Leaf A "));
        composite.add(new Leaf("Leaf B "));
         //为树根增加两个枝节点
        Composite compositeX = new Composite("Branch X");
        Composite compositeY = new Composite("Branch Y");
        composite.add(compositeX);
        composite.add(compositeY);

        //为BranchX增加叶节点
        compositeX.add(new Leaf("Leaf A In Branch X"));

        // 为BranchX增加枝节点
        Component branchZ = new Composite("Branch Z in Branch X");
        compositeX.add(branchZ);

        // 为BranchY增加叶节点
        compositeY.add(new Leaf("Leaf in Branch Y"));

        // 为BranchZ增加叶节点
        branchZ.add(new Leaf("Leaf in Branch Z"));
        composite.disPlay(1);

        doNext();

    }

    /**
     09-20 21:32:54.320 19896-19896/? I/System.out: 北京总公司
     09-20 21:32:54.320 19896-19896/? I/System.out: --总公司人力资源部
     09-20 21:32:54.320 19896-19896/? I/System.out: --总公司财务部
     09-20 21:32:54.320 19896-19896/? I/System.out: --山东分公司
     09-20 21:32:54.320 19896-19896/? I/System.out: ----山东分公司人力资源部
     09-20 21:32:54.320 19896-19896/? I/System.out: ----山东分公司账务部
     09-20 21:32:54.320 19896-19896/? I/System.out: ----济南办事处
     09-20 21:32:54.320 19896-19896/? I/System.out: ------济南办事处财务部
     09-20 21:32:54.320 19896-19896/? I/System.out: ------济南办事处人力资源部
     09-20 21:32:54.320 19896-19896/? I/System.out: ----枣庄办事处
     09-20 21:32:54.320 19896-19896/? I/System.out: ------枣庄办事处财务部
     09-20 21:32:54.320 19896-19896/? I/System.out: ------枣庄办事处人力资源部
     09-20 21:32:54.320 19896-19896/? I/System.out: --上海华东分公司
     09-20 21:32:54.320 19896-19896/? I/System.out: ----上海华东分公司人力资源部
     09-20 21:32:54.320 19896-19896/? I/System.out: ----上海华东分公司账务部
     09-20 21:32:54.320 19896-19896/? I/System.out: ----杭州办事处
     09-20 21:32:54.320 19896-19896/? I/System.out: ------杭州办事处财务部
     09-20 21:32:54.320 19896-19896/? I/System.out: ------杭州办事处人力资源部
     09-20 21:32:54.320 19896-19896/? I/System.out: ----南京办事处
     09-20 21:32:54.320 19896-19896/? I/System.out: ------南京办事处财务部
     09-20 21:32:54.320 19896-19896/? I/System.out: ------南京办事处人力资源部
     */
    private void doNext() {
        Company root = new ConcreteCompany();
        root.setName("北京总公司");
        root.add(new HRDepartment("总公司人力资源部"));
        root.add(new FinanceDepartment("总公司财务部"));
        Company shandongCom = new ConcreteCompany("山东分公司");
        shandongCom.add(new HRDepartment("山东分公司人力资源部"));
        shandongCom.add(new FinanceDepartment("山东分公司账务部"));
        Company zaozhuangCom = new ConcreteCompany("枣庄办事处");
        zaozhuangCom.add(new FinanceDepartment("枣庄办事处财务部"));
        zaozhuangCom.add(new HRDepartment("枣庄办事处人力资源部"));
        Company jinanCom = new ConcreteCompany("济南办事处");
        jinanCom.add(new FinanceDepartment("济南办事处财务部"));
        jinanCom.add(new HRDepartment("济南办事处人力资源部"));
        shandongCom.add(jinanCom);
        shandongCom.add(zaozhuangCom);
        Company huadongCom = new ConcreteCompany("上海华东分公司");
        huadongCom.add(new HRDepartment("上海华东分公司人力资源部"));
        huadongCom.add(new FinanceDepartment("上海华东分公司账务部"));
        Company hangzhouCom = new ConcreteCompany("杭州办事处");
        hangzhouCom.add(new FinanceDepartment("杭州办事处财务部"));
        hangzhouCom.add(new HRDepartment("杭州办事处人力资源部"));
        Company nanjingCom = new ConcreteCompany("南京办事处");
        nanjingCom.add(new FinanceDepartment("南京办事处财务部"));
        nanjingCom.add(new HRDepartment("南京办事处人力资源部"));
        huadongCom.add(hangzhouCom);
        huadongCom.add(nanjingCom);
        root.add(shandongCom);
        root.add(huadongCom);
        root.display(0);
    }
}
