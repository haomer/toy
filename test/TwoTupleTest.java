package com.zhanghao.test;


/**
 * Created by zhanghao on 16/11/18.
 */
class TwoTuple<A, B> {
    public final A first;
    public final B second;

    public TwoTuple(A a, B b) {
        first = a;
        second = b;
    }

    @Override
    public String toString() {
        return "TwoTuple{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}

public class TwoTupleTest{
    public TwoTuple<Integer, String> test(){
        TwoTuple<Integer, String> s = new TwoTuple(new Apply(),"21");
        return s;
    }

    public static void main(String[] args){
        TwoTuple twoTuple = new TwoTupleTest().test();
        System.out.println(twoTuple);
    }
}