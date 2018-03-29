package com.shiming.andrioddesignpattern.strategy_model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author： Created by shiming on 2018/3/29 16:23
 * mailbox：lamshiming@sina.com
 * 这是有效价格区间的注解，可以给策略添加有效区间的设置
 *
 */
//表示只能给类添加该注解
@Target(ElementType.TYPE)//java 1.7 可以支持了多注解了
@Retention(RetentionPolicy.RUNTIME)//这个必须要将注解保留在运行时
public @interface PriceRegion {
    int max() default 50000;
    int min() default 500;

}
