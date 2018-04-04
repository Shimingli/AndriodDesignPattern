package com.shiming.andrioddesignpattern.proxy_model;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shiming.andrioddesignpattern.R;
import com.shiming.andrioddesignpattern.ui.BaseFragment;

import java.lang.reflect.Proxy;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by shiming on 2017/9/17.
 *
 * 代理(Proxy)是一种设计模式,提供了对目标对象另外的访问方式;即通过代理对象访问目标对象.
 * 这样做的好处是:可以在目标对象实现的基础上,增强额外的功能操作,即扩展目标对象的功能.
 * 静态代理 :静态代理在使用时,需要定义接口或者父类,被代理对象与代理对象一起实现相同的接口或者是继承相同父类.
 *
 * 动态代理使用步骤
 * 1、定义委托类和公众接口
 * 2、字定义一个类 DynamicProxySubject 实现 invocationhandler 接口调用处理类
 * 然后在invoke方法中实现自己想要的任务
 * 3、通过Proxy.newProxyInstance()方法生成代理类，代理对象;
 *
 *
 * 远程代理：可以为一个位于不同的地址空间的对象提供一个本地代理的对象，远程代理将网络或者是通信的细节隐藏起来
 * 这里我就写了一个aidl来表示
 * ActivityManagerService
 * todo 为什么我要把这个fragment放到这里，那是因为我这里写了一个aidl的例子，为了保证能调用到aidl
 *
 * 优点： 1、职责清晰。 2、高扩展性。 3、智能化。
 缺点： 1、由于在客户端和真实主题之间增加了代理对象，因此有些类型的代理模式可能会造成请求的处理速度变慢。
 2、实现代理模式需要额外的工作，有些代理模式的实现非常复杂。
 *
 */

public class ProxyModelFragment extends BaseFragment {

    private IMyAidlInterface mCatService;
    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.proxy_model_fragment_layout,null,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //静态代理
        StaticProxySubject proxySubject = new StaticProxySubject();
        //通过代理访问
        proxySubject.request();
        //输出日记
        //        09-17 17:14:55.452 27113-27113/? D/StaticRealSubject: request
        //动态代理
        DynamicProxySubject dynamicProxySubject = new DynamicProxySubject();
        /*
        laoder：类加载器，主要是将生成的代理类的字节码装载到jvm
        *   public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                         InvocationHandler h)
        * */
        DynamicSubject dynamicSubject = (DynamicSubject) Proxy.newProxyInstance(DynamicRealSubject.class.getClassLoader(), DynamicRealSubject.class.getInterfaces(), dynamicProxySubject);
        dynamicSubject.request();

        //输出日记

//        09-17 17:14:55.452 27113-27113/? D/DynamicRealSubject: request

        //aidl远程代理
        Intent intent = new Intent(getActivity(), MyService.class);
        getActivity().bindService(intent, conn, BIND_AUTO_CREATE);

        getView().findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRemoteService(getView());
            }
        });


    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mCatService = IMyAidlInterface.Stub.asInterface(service);
            //com.shiming.andrioddesignpattern.proxy_model.MyService$CatBinder@a6d543e8
            System.out.println("shiming==" +mCatService);


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mCatService = null;

        }
    };

    public void getRemoteService(View view)
    {
        try
        {
            Toast.makeText(getContext(),"网络代理："+mCatService.getWhat()+":",
                    Toast.LENGTH_LONG).show();
        }catch (RemoteException e)
        {
            e.printStackTrace();
        }

    }
}
