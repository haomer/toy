package com.zhanghao.threads;

/**
 * Created by zhanghao on 16/11/24.
 */
public abstract class IntGenerator {
    private  volatile boolean canceled = false;
    public abstract int next();
    public void cancel(){ canceled = true;}
    public boolean isCanceled(){
        return  canceled;
    }
}
