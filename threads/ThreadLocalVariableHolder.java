package com.zhanghao.threads;

import org.apache.catalina.Executor;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhanghao on 16/11/24.
 */

class Accessor implements Runnable{
    private final int id;
    public Accessor(int idn){
        id = idn;
    }
    @Override
    public void run() {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
    }
    public String toString(){
        return "#" + id + ": " + ThreadLocalVariableHolder.get();
    }
}

public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        protected synchronized Integer initialValue(){
            return 2;
        }
    };
    public static void increment(){
        value.set(value.get() + 1);
    }
    public static int get(){
        return value.get();
    }
    public static void main(String... args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i< 5; i++){
            exec.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(3);
        exec.shutdown();
    }

}
