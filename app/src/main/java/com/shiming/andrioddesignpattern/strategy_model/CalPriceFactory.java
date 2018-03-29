package com.shiming.andrioddesignpattern.strategy_model;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * author： Created by shiming on 2018/3/29 16:31
 * mailbox：lamshiming@sina.com
 */
//工厂模式
public class CalPriceFactory {
    private static final String CAL_PRICE_PACKAGE = "com.shiming.andrioddesignpattern.strategy_model";//这里是一个常量，表示我们扫描策略的包

    private ClassLoader classLoader = getClass().getClassLoader();

    private List<Class<? extends INewPriceStrategy>> calPriceList;//策略列表

    //根据玩家的总金额产生相应的策略
    public INewPriceStrategy createCalPrice(Player player) {
        //在策略列表查找策略
        for (Class<? extends INewPriceStrategy> clazz : calPriceList) {
            PriceRegion validRegion = handleAnnotation(clazz);//获取该策略的注解
            //判断金额是否在注解的区间
            if (player.getTotalAmount() > validRegion.min() && player.getTotalAmount() < validRegion.max()) {
                try {
                    //是的话我们返回一个当前策略的实例
                    return clazz.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("策略获得失败");
                }
            }
        }
        throw new RuntimeException("策略获得失败");
    }

    //处理注解，我们传入一个策略类，返回它的注解
    private PriceRegion handleAnnotation(Class<? extends INewPriceStrategy> clazz) {
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        if (annotations == null || annotations.length == 0) {
            return null;
        }
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof PriceRegion) {
                return (PriceRegion) annotations[i];
            }
        }
        return null;
    }

    //单例
    private CalPriceFactory() {
        init();
    }

    //在工厂初始化时要初始化策略列表
    private void init() {
        calPriceList = new ArrayList<Class<? extends INewPriceStrategy>>();
        File[] resources = getResources();//获取到包下所有的class文件
        Class<INewPriceStrategy> calPriceClazz = null;
        try {
            calPriceClazz = (Class<INewPriceStrategy>) classLoader.loadClass(INewPriceStrategy.class.getName());//使用相同的加载器加载策略接口
        } catch (ClassNotFoundException e1) {
            throw new RuntimeException("未找到策略接口");
        }
        for (int i = 0; i < resources.length; i++) {
            try {
                //载入包下的类
                Class<?> clazz = classLoader.loadClass(CAL_PRICE_PACKAGE + "." + resources[i].getName().replace(".class", ""));
                //判断是否是CalPrice的实现类并且不是CalPrice它本身，满足的话加入到策略列表
                if (INewPriceStrategy.class.isAssignableFrom(clazz) && clazz != calPriceClazz) {
                    calPriceList.add((Class<? extends INewPriceStrategy>) clazz);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //获取扫描的包下面所有的class文件
    private File[] getResources() {
        try {
            File file = new File(classLoader.getResource(CAL_PRICE_PACKAGE.replace(".", "/")).toURI());
            return file.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    if (pathname.getName().endsWith(".class")) {//我们只扫描class文件
                        return true;
                    }
                    return false;
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException("未找到策略资源");
        }
    }

    public static CalPriceFactory getInstance() {
        return new CalPriceFactory();
    }

}
