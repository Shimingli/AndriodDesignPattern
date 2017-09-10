package com.shiming.andrioddesignpattern.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.builder_model.Builder;
import com.shiming.andrioddesignpattern.builder_model.ConcreteBuilder;
import com.shiming.andrioddesignpattern.builder_model.Diretor;
import com.shiming.andrioddesignpattern.builder_model.Person;

/**
 * Created by shiming on 2017/9/10.
 * 建造者模式：单独的来对一个对象进行构造，将一个复杂的构建与其表示相分离
 * ，使其同样的构建过程可以构建不同的表示对象
 *
 *
 * dialog和Notification 的源码都使用到了建造者模式，可以详细的理解
 *
 * 理解：是将一个复杂的对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
 * 建造者模式的好处就是保证了流程不会变化，流程即不会增加、也不会遗漏或者产生流程次序错误
 */

public class BuilderModelFragment extends BaseFragment {
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.builder_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView) getView().findViewById(R.id.tv);
        //通过具体创建者，得到创建者
        Builder builder=new ConcreteBuilder();
        //通过创建者，得到导演者
        Diretor diretor = new Diretor(builder);
        //做什么，做完了以后，其实里面的builder已经有属性了
        diretor.construct("shiming",true,18,170,80);
        //通过创建者创建实例
        Person person = builder.createPerson();
        textView.setText("我是通过建造者创建的人：" +person.toString());
        getView().findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("Title");
                builder.setMessage("Message");
                builder.setPositiveButton("Button1",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getContext(),"点击了对话框上的Button1",Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNeutralButton("Button2",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                Toast.makeText(getContext(),"点击了对话框上的Button2",Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setNegativeButton("Button3",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getContext(),"点击了对话框上的Button3",Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.create().show();
            }
        });
    }
}
