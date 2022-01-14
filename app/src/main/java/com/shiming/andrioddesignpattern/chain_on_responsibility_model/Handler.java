package com.shiming.andrioddesignpattern.chain_on_responsibility_model;

/**
 * Created by shiming on 2017/9/25.
 */

public abstract class Handler {
    /**
     * 持有下一个处理请求的对象
     */
    protected Handler mLastHandler = null;

    public Handler getLastHandler() {
        return mLastHandler;
    }

    public void setLastHandler(Handler handler) {
        mLastHandler = handler;
    }

    /***
     *
     * @param user 声请人
     * @param moneny 多少钱
     * @return 返回处理的结果
     */
    public abstract String handleRequest(String user, double moneny);

}
