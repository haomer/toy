package com.zhanghao.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhanghao on 16/11/23.
 */
public class FixedThreadPool {
    public static void main(String... args){
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for(int i = 0; i<5 ; i++){
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
