package com.shiming.andrioddesignpattern.chain_on_responsibility_model;

/**
 * Created by shiming on 2017/9/25.
 */

public class ProjectManager extends Handler {
    @Override
    public String handleRequest(String user, double moneny) {
        String str = "";
        //最大的值我只能帮你申请到1000内的钱
        if (moneny < 1000) {
            if (user.equals("shiming")) {
                str = "ProjectManager同意" + user + "申请的钱一共：" + moneny;
            } else {
                //其他人不同意
                str = "ProjectManager不同意" + user + "申请的钱一共：" + moneny;
            }
        } else {
            //其他的钱我只能让别人帮你哦
            if (getLastHandler() != null) {
                return getLastHandler().handleRequest(user, moneny);
            }
        }

        return str;
    }
}
