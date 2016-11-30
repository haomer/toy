package com.zhanghao.tree;

/**
 * Created by zhanghao on 16/11/30.
 */

//
//class BinaryNode{
//    Object element;
//    BinaryNode left;
//    BinaryNode right;
//}

public class TreeTest {
    private static class BinaryNode<AnyType>{
        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        BinaryNode(AnyType theElement){
            this(theElement, null, null);
        }
        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt){
            element = theElement;
            left = lt;
            right = rt;
        }
    }
    public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>{
//        public static class BinaryTree<AnyType>;
    }
}
