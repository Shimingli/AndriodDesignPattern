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
import com.shiming.andrioddesignpattern.builder_model.Director;
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
 *
 * 优点：
 易于解耦
 将产品本身与产品创建过程进行解耦，可以使用相同的创建过程来得到不同的产品。也就说细节依赖抽象。
 易于精确控制对象的创建
 将复杂产品的创建步骤分解在不同的方法中，使得创建过程更加清晰
 易于拓展
 增加新的具体建造者无需修改原有类库的代码，易于拓展，符合“开闭原则“。
 缺点：
 建造者模式所创建的产品一般具有较多的共同点，其组成部分相似；如果产品之间的差异性很大，则不适合使用建造者模式，
 因此其使用范围受到一定的限制。
 如果产品的内部变化复杂，可能会导致需要定义很多具体建造者类来实现这种变化，导致系统变得很庞大。

 应用场景：
 需要生成的产品对象有复杂的内部结构，这些产品对象具备共性；
 隔离复杂对象的创建和使用，并使得相同的创建过程可以创建不同的产品。
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
        Director diretor = new Director(builder);
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
