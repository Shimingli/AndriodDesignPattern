package com.shiming.andrioddesignpattern.observer_model;


import java.util.ArrayList;
import java.util.List;

public class BluetoothStateObserver {

    private BluetoothStateObserver() {

    }

    public static BluetoothStateObserver getInstance() {
        return BluetoothStateObserverHolder.instance;
    }

    private static class BluetoothStateObserverHolder {
        private static BluetoothStateObserver instance = new BluetoothStateObserver();
    }


    private List<OnBlufiNotifyListener> onBlufiNotifyEventList = new ArrayList<>();


    public void registerObserver(OnBlufiNotifyListener o) {
        onBlufiNotifyEventList.add((OnBlufiNotifyListener) o);
    }


    public void removeObserver(OnBlufiNotifyListener o) {
        onBlufiNotifyEventList.remove(o);
    }

    public void notifyBlufiNotificationObserver() {

        for (int i = 0; i < onBlufiNotifyEventList.size(); i++) {
//            OnBlufiNotifyListener onBlufiNotifyListener = onBlufiNotifyEventList.get(i);
//            onBlufiNotifyListener.onBlufiNotify(1, 2);
//            System.out.println(" Shiming " + onBlufiNotifyEventList.size());
        }

        for (int i = onBlufiNotifyEventList.size() - 1; i >= 0; i--) {
            OnBlufiNotifyListener onBlufiNotifyListener = onBlufiNotifyEventList.get(i);
            onBlufiNotifyListener.onBlufiNotify(1, 2);
            System.out.println(" Shiming " + onBlufiNotifyEventList.size());
        }

    }

    public interface OnBlufiNotifyListener {

        void onBlufiNotify(int packageType, int subType);

        void onBlufiNotifyError(int errorCode, byte[] responseData);
    }

}
