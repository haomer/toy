
import java.util.Stack;

/**
 * Created by zhanghao on 16/8/31.
 */
public class BinaryTreeDemo {
    private TreeNode root = null;

    public BinaryTreeDemo() {
        this.root = new TreeNode(1, "A");
    }

    /**
     * 创建一棵二叉树
     *          A
     *    B          C
     * D     E           F
     *
     * @param treeNode
     */
    public void createBinTree(TreeNode treeNode) {
        TreeNode newNodeB = new TreeNode(2, "B");
        TreeNode newNodeC = new TreeNode(3, "C");
        TreeNode newNodeD = new TreeNode(4, "D");
        TreeNode newNodeE = new TreeNode(5, "E");
        TreeNode newNodeF = new TreeNode(6, "F");
        root.leftChild = newNodeB;
        root.rightChild = newNodeC;
        root.leftChild.leftChild = newNodeD;
        root.leftChild.rightChild = newNodeE;
        root.rightChild.rightChild = newNodeF;
    }

    public void buildTree(TreeNode node, String data){
        if(root == null){
            root = node;
        }else {
            if(data.compareTo(node.data) < 0){
                if(node.leftChild == null){
                    node.leftChild = new TreeNode(11, data);
                }else {
                    buildTree(node.leftChild, data);
                }
            }else {
                if(node.leftChild == null){
                    node.rightChild = new TreeNode(11, data);
                }else {
                    buildTree(node.rightChild, data);
                }
            }
        }
    }

    //判断是否为空
    public boolean isEmpty() {
        return root == null;
    }

    //树的高度
    public int height() {
        return height(root);
    }

    private int height(TreeNode subTree) {
        if (subTree == null) {
            return 0;
        } else {
            int i = height(subTree.leftChild);
            int j = height(subTree.rightChild);
            return (i < j) ? (j + 1) : (i + 1);
        }
    }

    //节点个数
    public int size() {
        return size(root);
    }

    private int size(TreeNode subTree) {
        if (subTree == null) {
            return 0;
        } else {
            return 1 + size(subTree.leftChild)
                    + size(subTree.rightChild);
        }
    }

    //返回双亲节点
    public TreeNode parent(TreeNode element){
        return (root == null || root ==element) ? null:parent(root, element);
    }
    public TreeNode parent(TreeNode subTree, TreeNode element){
        if(subTree == null){
            return null;
        }
        if (subTree.leftChild==element || subTree.rightChild == element){
            return subTree;
        }
        TreeNode p;
        if((p=parent(subTree.leftChild, element))!=null)
            //递归在左子树中搜索
            return p;
        else
            //递归在右子树中搜索
            return parent(subTree.rightChild, element);
    }
    //返回左子节点
    public TreeNode getLeftChildNode(TreeNode element){
        return (element != null)?element.leftChild:null;
    }
    //返回右子节点
    public TreeNode getRightChildNode(TreeNode element){
        return (element!=null)?element.rightChild:null;
    }

    //返回root节点
    public TreeNode getRoot(){
        return root;
    }

    //释放某个节点，该节点的左右子节点都需要释放
    //采取后续遍历
    public void destory(TreeNode subTree){
        if(subTree!=null){
            destory(subTree.leftChild);
            destory(subTree.rightChild);
            subTree = null;
        }
    }
    public void traverse(TreeNode subTree){
        System.out.println("key:"+subTree.key+"--name:"+subTree.data);;
        traverse(subTree.leftChild);
        traverse(subTree.rightChild);
    }
    //前序遍历
    public void preOrder(TreeNode subTree) {
        if (subTree != null) {
            visted(subTree);
            preOrder(subTree.leftChild);
            preOrder(subTree.rightChild);
        }
    }
    //中序遍历
    public void inOrder(TreeNode subTree) {
        if (subTree != null) {
            inOrder(subTree.leftChild);
            visted(subTree);
            inOrder(subTree.rightChild);
        }
    }
    //后序遍历
    public void postOrder(TreeNode subTree){
        if(subTree != null){
            postOrder(subTree.leftChild);
            postOrder(subTree.rightChild);
            visted(subTree);
        }
    }

    //前序遍历非递归实现
    public void nonRecPreOrder(TreeNode p){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = p;
        while(node != null || stack.size()>0){
            while ( node!=null){
                visted(node);
                stack.push(node);
                node=node.leftChild;
            }
            //栈非空
            while(stack.size()>0){
                node=stack.pop();
                visted(node);
                node=node.rightChild;
            }
        }
    }
    //中序遍历非递归实现
    public void nonRecInOrder(TreeNode p){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = p;
        //存在左子树
        while (node != null || stack.size()>0){
            while (node!=null){
                stack.push(node);
                node = node.leftChild;
            }
            //栈非空
            if(stack.size()>0){
                node=stack.pop();
                visted(node);
                node=node.rightChild;
            }
        }
    }
    //后序遍历的非递归实现
    public void noRecPostOrder(TreeNode p){
        Stack<TreeNode> stack=new Stack<TreeNode>();
        TreeNode node =p;
        while(p!=null){
            //左子树入栈
            for(;p.leftChild!=null;p=p.leftChild){
                stack.push(p);
            }
            //当前结点无右子树或右子树已经输出
            while(p!=null&&(p.rightChild==null||p.rightChild==node)){
                visted(p);
                //纪录上一个已输出结点
                node =p;
                if(stack.empty())
                    return;
                p=stack.pop();
            }
            //处理右子树
            stack.push(p);
            p=p.rightChild;
        }
    }
    public void visted(TreeNode subTree) {
        subTree.isVisted = true;
        System.out.println("key:" + subTree.key + "--name:" + subTree.data);
    }

    //节点数据结构
    private class TreeNode {
        private int key = 0;
        private String data = null;
        private boolean isVisted = false;
        private TreeNode leftChild = null;
        private TreeNode rightChild = null;

        public TreeNode() {
        }

        /**
         * @param key  层序编码
         * @param data 数据域
         */
        public TreeNode(int key, String data) {
            this.key = key;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    public static void main(String[] args) {
        BinaryTreeDemo bt = new BinaryTreeDemo();
        bt.createBinTree(bt.root);
        bt.postOrder(bt.root);
    }
}

