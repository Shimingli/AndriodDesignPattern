package com.shiming.andrioddesignpattern.Visitor_pattern;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.ui.BaseFragment;


/**
 * author： Created by shiming on 2018/3/26 18:57
 * mailbox：lamshiming@sina.com
 * 访问者模式：封装一些作用于某种数据结构中的各元素的操作，它可以在不改变这个数据结构的前提下定义
 * 作用于这些元素的新的操作
 * 使用的场景：
 * 1.对象结构比较稳定，但是经常需要在此对象结构上定义新的操作
 * 2、需要对一个对象结构中的对象进行很多不同的且不相关的操作，而需要避免这些操作污染这些对象的类，也不希望在增加
 * 新的操作时修改这些类
 *
 * ButterKnife、Dagger、Retrofit都是基于APT（Annotation Processing Tools）实现。而编译注解核心依赖APT。
 * 当我们通过APT处理注解时，最终会将获取到的元素转换为相应的Element元素，以便获取到它们对应信息
 *
 * 访问者模式的优缺点
 * 优点：
 * 1、是的数据结构和作用于结构上的操作解耦，使得操作集合可以独立变化
 * 2、添加新的操作或者说访问者会非常的容易
 * 3、将对各个元素的一组操作集中在一个访问类中，使得类层次结构不改变的情况下，可以针对各个层次做出不同操作，而不影响类层次结构的完整性
 * 缺点：
 * 1、增加新的元素比较困难，
 * 2、实现起来比较复杂，会增加系统的复杂性
 * 通用性：
 * 1、但数据结构比较稳定，作用于数据结构的操作经常变化的时候
 * 2、当一个数据结构，一些元素类需要负责与其不相关的操作时候，为了将这些操作分离出去
 */
//package javax.lang.model.element;
//public interface Element extends AnnotatedConstruct {
//    TypeMirror asType();
//
//    ElementKind getKind();   ElementKind getKind();//获取元素类型
//
//    Set<Modifier> getModifiers();
//
//    Name getSimpleName();
//
//    Element getEnclosingElement();
//
//    List<? extends Element> getEnclosedElements();
//
//    boolean equals(Object var1);
//
//    int hashCode();
//
//    List<? extends AnnotationMirror> getAnnotationMirrors();
//
//    <A extends Annotation> A getAnnotation(Class<A> var1);
//
//    <R, P> R accept(ElementVisitor<R, P> var1, P var2);  //接受访问者的访问
//}
public class VisitorPatternModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.visitor_pattern_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //账本
        AccountBook accountBook = new AccountBook();
        //收入
        accountBook.addBill(new IncomeBill(1000,"卖东西"));
        accountBook.addBill(new IncomeBill(1000,"卖guanggao"));
        //支出
        accountBook.addBill(new ConsumeBill(200,"工资1"));
        accountBook.addBill(new ConsumeBill(200,"工资2"));

        BossVisitor bossVisitor = new BossVisitor();
        CPA cpa = new CPA();
        //   //两个访问者分别访问账本
        accountBook.show(bossVisitor);
        accountBook.show(cpa);
        double totalConsume = bossVisitor.getTotalConsume();
        double totalIncome = bossVisitor.getTotalIncome();
//        03-26 11:36:46.521 3166-3166/com.shiming.andrioddesignpattern I/System.out: 老板查看一共花费多少，数目是：400.0
//        03-26 11:36:46.521 3166-3166/com.shiming.andrioddesignpattern I/System.out: 老板查看一共收入多少，数目是：2000.0
//        03-26 11:36:46.521 3166-3166/com.shiming.andrioddesignpattern I/System.out: bossVisitor ==totalConsume400.0
//        03-26 11:36:46.521 3166-3166/com.shiming.andrioddesignpattern I/System.out: bossVisitor ==totalIncome2000.0
        System.out.println("bossVisitor ==totalConsume" +totalConsume);
        System.out.println("bossVisitor ==totalIncome" +totalIncome);

    }
}
