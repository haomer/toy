package com.zhanghao.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhanghao on 16/11/23.
 */
public class MainThread {
    public static void main(String... args){
//        LiftOff launch = new LiftOff();
//        launch.run();
//        Thread t = new Thread(new LiftOff());
//        t.start();
//        for(int i = 0; i < 5; i++)
//            new Thread(new LiftOff()).start();
//        System.out.println("Waiting for LiftOff");

        ExecutorService ecec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++)
            ecec.execute(new LiftOff());

        ecec.execute(new LiftOff());
        ecec.shutdown();

    }
}
