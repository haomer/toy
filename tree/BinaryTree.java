package com.zhanghao.tree;

import java.util.Stack;

/**
 * Created by zhanghao on 16/11/30.
 */
public class BinaryTree {
    private TreeNode root = null;

    public BinaryTree() {
        this.root = new TreeNode(1);
    }
    public void createBinTree(TreeNode treeNode) {
        TreeNode newNodeB = new TreeNode(2);
        TreeNode newNodeC = new TreeNode(3);
        TreeNode newNodeD = new TreeNode(4);
        TreeNode newNodeE = new TreeNode(5);
        TreeNode newNodeF = new TreeNode(6);
        root.leftChild = newNodeB;
        root.rightChild = newNodeC;
        root.leftChild.leftChild = newNodeD;
        root.leftChild.rightChild = newNodeE;
        root.rightChild.rightChild = newNodeF;
    }
    //在某个节点之后添加数据
    public void buildTree(TreeNode node, Integer data){
        if(root == null){
            root = node;
        }else {
            if(data.compareTo(node.data) < 0){
                if(node.leftChild == null){
                    node.leftChild = new TreeNode(data);
                }else {
                    buildTree(node.leftChild, data);
                }
            }else {
                if(node.leftChild == null){
                    node.rightChild = new TreeNode(data);
                }else {
                    buildTree(node.rightChild, data);
                }
            }
        }
    }
    //判断该树 是否为空
    public boolean isEmpty(){
        return root == null;
    }
    //判断该数的深度
    public int heigh(){
        return heigh(root);
    }

    private int heigh(TreeNode sub){
        if(sub == null){
            return 0;
        }else {
            int i = heigh(sub.leftChild);
            int j = heigh(sub.rightChild);
            return i < j ? j+1 : i+1 ;
        }
    }
    // 节点的个数
    public int size(){
        return size(root);
    }
    private int size(TreeNode treeNode){
        if(treeNode == null){
            return 0;
        }else {
            return 1 + size(treeNode.leftChild) + size(treeNode.rightChild);
        }
    }
    //返回双亲节点
    public TreeNode parent(TreeNode sub){
        return root == null || root == sub ? null : parent(root, sub);
    }

    private TreeNode parent(TreeNode sub, TreeNode element){
        if(sub == null){
            return null;
        }
        if(sub.leftChild == element || sub.rightChild == element){
            return sub;
        }
        TreeNode p = parent(sub.leftChild, sub);
        if(p != null){
            return p;
        }else {
            return parent(sub.rightChild, element);
        }
    }
    //返回左子节点
    public TreeNode getLeft(TreeNode element){
        return (element !=null) ? element.leftChild : null;
    }
    //返回又子节点
    public TreeNode getRight(TreeNode element){
        return (element != null) ? element.rightChild : null;
    }
    //返回root 节点
    public TreeNode getRoot(){
        return root != null ? root : null;
    }

    private void toString(TreeNode node){
        if(node != null) {
            System.out.println("data: " + node.data);
        }
    }
    //翻转
    public void traverse(){
        traverse(root);
    }
    private void traverse(TreeNode subTree){
        System.out.println("data: "+subTree.data);
        traverse(subTree.leftChild);
        traverse(subTree.rightChild);
    }
    //先序遍历
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(TreeNode node){
        if(node != null){
            toString(node);
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }
    //非递归先序遍历
    public void nonrecurPreOrder(){
        nonrecurPreOrder(root);
    }
    private void nonrecurPreOrder(TreeNode node){
        if(node == null){
            return;
        }
        TreeNode p = node;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(p);
        while (stack.isEmpty() != true){
            p = stack.pop();
            toString(p);
            if(p.rightChild != null){
                stack.push(p.rightChild);
            }
            if(p.leftChild != null){
                stack.push(p.leftChild);
            }
        }
    }

    //中序遍历
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(TreeNode node){
        if(node != null){
            inOrder(node.leftChild);
            toString(node);
            inOrder(node.rightChild);
        }
    }

    //非递归中序遍历
    public void nonrecurInOrder(){
        nonrecurInOrder(root);
    }
    private void nonrecurInOrder(TreeNode node){
        if(node == null){
            return;
        }
        TreeNode current = node;
        Stack<TreeNode> stack = new Stack<>();
        while (current != null || stack.isEmpty()){
            while (current != null){
                stack.push(current);
                current = current.leftChild;
            }
            if(current == null){
                TreeNode n = stack.pop();
                toString(n);
                current = n.rightChild;
            }
        }
    }


    //后续遍历
    public void laterOrder(){
        laterOrder(root);
    }
    private void laterOrder(TreeNode node){
        if(node != null){
            laterOrder(node.leftChild);
            laterOrder(node.rightChild);
            toString(node);
        }
    }

    //非递归后续遍历
    public void nonrecurLaterOrder(){
        nonrecurLaterOrder(root);
    }

    private void nonrecurLaterOrder(TreeNode node){
        if(node==null){
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = node;
        while(current !=null || stack.isEmpty()!=true){
            //step 1
            while(current!=null){
                if(current.rightChild!=null){
                    stack.push(current.rightChild);
                }
                stack.push(current);
                current = current.leftChild;
            }

            // step2 既然出栈了，该节点肯定没有左孩子。
            current = stack.pop();
            if(current.rightChild!=null && !stack.isEmpty() && current.rightChild == stack.peek())  {
                stack.pop(); //出栈右孩子
                stack.push(current);
                current = current.rightChild;
            }
            else{
                toString(current);
                current = null;
            }
        }

    }

    //节点数据结构
    private class TreeNode {
        private Integer data = null;
        private TreeNode leftChild = null;
        private TreeNode rightChild = null;

        public TreeNode() {
        }

        /**
         * @param data 数据域
         */
        public TreeNode(int data) {
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    public static void main(String... args){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.createBinTree(binaryTree.getRoot());
        binaryTree.laterOrder();
    }
}
