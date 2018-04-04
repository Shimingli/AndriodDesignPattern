package com.shiming.andrioddesignpattern.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.prototype_model.ConcreteProtoType;
import com.shiming.andrioddesignpattern.prototype_model.ProtoType;
import com.shiming.andrioddesignpattern.prototype_model.ProtoTypeExtend;

/**
 * Created by shiming on 2017/9/12.
 * protoType :原型 用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象
 * 原型模式：包括三个角色
 * 1、客户：client 使用原型对象的客户程序
 * 2、抽象原型：ProtoType 规定了具体原型对象必须实现的接口
 * 3、具体原型：concretePrototype 是客户端使用的对象，即被复制的对象
 * 好处：使用原型的模式创建对象比直接new一个对象的性能上要好很多，因为object是一个本地的方法
 * ，它直接操作的是内存中的二进制流，特别是复制大的对象，性能上特别明显。如果需要循环在体内创建对象的话，
 * 假如对象的创建过程比较复杂，或者循环的次数特别多的话，使用原型模式不但可以简化创建的过程，而且可以使系统的
 * 整体的性能提高很多
 *
 * 注意的问题：使用原型模式复制对象，不会调用对象的构造方法，因为对象的复制是通过调用object类中
 * clone的方法完成，他直接在内存中复制数据的，不会调用到类的构造的方法，而且访问的权限对原型模式无效
 * 在单利模式中构造的方法访问权限是private的，但是原型模式直接无视构造方法，所以单利模式和原型模式是
 * 冲突的，使用这个模式的时候，需要特别的注意
 * Bundle类，该类实现了Cloneable接口  Intent类，该类也实现了Cloneable接口
 * OkHttpClient这个类中，它实现了Cloneable接口（okhttp）
 *
 * 优点： 1、性能提高。 2、逃避构造函数的约束。
 缺点：
 1、配备克隆方法需要对类的功能进行通盘考虑，这对于全新的类不是很难，但对于已有的类不一定很容易，特别当一个类引用不支持串行化的间接对象，或者引用含有循环结构的时候。
 2、必须实现 Cloneable 接口。
 *
 */

public class ProtoTypeModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.proto_type_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView) getView().findViewById(R.id.tv);
        ConcreteProtoType concreteProtoType = new ConcreteProtoType();
        String s = concreteProtoType.getS();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0;i<10;i++) {
            ProtoType clone = concreteProtoType.clone();
            stringBuffer.append(clone+"我是第"+i+"个克隆出来的"+"\n");
        }

        textView.setText("第一种的实现的方式"+s+""+"\n"+"stringBuffer=="+stringBuffer);

        TextView textView1 = (TextView) getView().findViewById(R.id.tv2);
        ProtoTypeExtend protoTypeExtend = new ProtoTypeExtend();
        String clone = protoTypeExtend.getClone();
        StringBuffer stringBuffer1 = new StringBuffer();
        for (int i=0;i<10;i++){
            ProtoTypeExtend clone1 = protoTypeExtend.clone();
            stringBuffer1.append(clone1+"我是第"+i+"个克隆出来的"+"\n");

        }
        stringBuffer1.append("java.lang.CloneNotSupportedException: Class doesn't implement Cloneable 必须实Cloneable接口");
        textView1.setText("第二种的实现的方式:经过测试无用"+clone+"\n"+"stringBuffer=="+stringBuffer1);
    }
}
