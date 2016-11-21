package com.zhanghao.test;

import java.util.Arrays;

/**
 * Created by zhanghao on 16/11/15.
 */
public class Apply {

    public static void main(String[] args){
        process(new Upcase(), s);
        process(new Downcase(), s);
        process(new Splitter(), s);

    }
    public static String s = "Desasa with hkhjk isj jkhjk";

    public static void process(Processor p, Object s){
        System.out.println(p.name());
        System.out.println(p.process(s));

        Thread x =  new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<10;i++){
                    System.out.println(i);
                }
            }
        });
        x.start();

    }

}

interface Processor{
    public String name();

    Object process(Object input);
}

class Upcase implements Processor{

    public String name(){
        return getClass().getSimpleName();
    }
    public String process(Object input){
        return ((String)input).toUpperCase();
    }
}
class Splitter implements Processor{

    public String name(){
        return getClass().getSimpleName();
    }
    public String process(Object input){
        return Arrays.toString(((String) input).split(" "));
    }
}
class Downcase implements Processor{

    public String name(){
        return getClass().getSimpleName();
    }
    public String process(Object input){
        return ((String)input).toLowerCase();
    }
}