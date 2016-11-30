package com.zhanghao.threads;

/**
 * Created by zhanghao on 16/11/24.
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }
    public static void main(String... args){
        EvenChecker.test(new EvenGenerator());
    }
}
