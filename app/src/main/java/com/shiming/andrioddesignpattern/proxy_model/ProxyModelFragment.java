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
 * 静态代理
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
//        09-17 17:14:55.452 27113-27113/? D/StaticRealSubject: request
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
