package com.zhanghao.test;

/**
 * Created by zhanghao on 16/11/18.
 */
public class LinkedStack<T> {
    private static class Node<U>{
        U item;
        Node<U> next;
        Node(){
            item = null;
            next = null;
        }
        Node(U item, Node<U> next){
            this.item = item;
            this.next = next;
        }
        boolean end(){
            return item == null && next == null;
        }
    }
    private Node<T> top = new Node<>();
    public void  push(T item){
        System.out.println(top.item);
        top = new Node<>(item, top);
    }

    public T pop(){
        T result = top.item;
        if(!top.end()){
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args){
        LinkedStack<String> linkedStack = new LinkedStack<>();
        for(String s : "Phasers or stun".split(" ")){
            linkedStack.push(s);
        }
        System.out.println(linkedStack);

    }
}
