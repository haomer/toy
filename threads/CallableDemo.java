package com.zhanghao.threads;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by zhanghao on 16/11/23.
 */
class TaskWithResult implements Callable<String>{
    private int id;
    public TaskWithResult(int id){
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return  Thread.currentThread().getName() + " : result of TaskWithResult " + id;
    }
}
public class CallableDemo {
    public static void main(String... args){
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<>();

        for(int i = 0; i< 10; i++){
            results.add(exec.submit(new TaskWithResult(i)));
        }
        for(Future<String> fs : results){
            try {
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
        }
    }
}
