package com.zhanghao.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhanghao on 16/11/24.
 */
public class SleepingTask extends LiftOff {
    public void run(){
        try{
            while(countDown-- > 0){
                System.out.println(Thread.currentThread()+":"+status());
                Thread.sleep(100);
            }
        }catch (InterruptedException e){
            System.out.println("Interrupted");
        }
    }
    public static void main(String... args){
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++){
            exec.execute(new SleepingTask());
        }
        exec.shutdown();
    }
}
