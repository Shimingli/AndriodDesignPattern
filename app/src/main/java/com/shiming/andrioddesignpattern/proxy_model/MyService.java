package com.shiming.andrioddesignpattern.proxy_model;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * 创建myservice 这就是远程代理
 */

public class MyService extends Service {
    private CatBinder catBinder;

    /**
     * 构造方法初始化，binder类，得到binder对象
     */
    public MyService() {
        catBinder = new CatBinder();
    }

    /**
     * onbind 返回一个binder对象
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return catBinder;
    }

    //创建自定义binder类继承aidl接口中的stub抽象类，重写aidl的抽象方法
    public class CatBinder extends IMyAidlInterface.Stub {

        @Override
        public String getWhat() throws RemoteException {
            return "我是aidl实现的哦";
        }
    }
}


