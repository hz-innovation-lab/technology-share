package 二叉树;

/**
 * @author baiyundou
 * @date 10:32 2019/10/22
 * @description
 */
public class TravseRecursion {

    public static void main(String[] args) {
        TreeNode treeNode = constructTreeNode();
        postTravse(treeNode);
    }

    private static void preTravse(TreeNode treeNode) {
        if(treeNode == null){
            return;
        }
        System.out.println(treeNode.val);
        preTravse(treeNode.left);
        preTravse(treeNode.right);
    }

    private static void middleTravse(TreeNode treeNode) {
        if(treeNode == null){
            return;
        }
        middleTravse(treeNode.left);
        System.out.println(treeNode.val);
        middleTravse(treeNode.right);
    }

    private static void postTravse(TreeNode treeNode) {
        if(treeNode == null){
            return;
        }
        postTravse(treeNode.left);
        postTravse(treeNode.right);
        System.out.println(treeNode.val);
    }

    private static TreeNode constructTreeNode() {
        TreeNode first1 = new TreeNode(1);
        TreeNode first2 = new TreeNode(2);
        TreeNode first3 = new TreeNode(3);
        TreeNode first4 = new TreeNode(4);
        TreeNode first5 = new TreeNode(5);
        TreeNode first6 = new TreeNode(6);
        first1.left = first2;
        first1.right = first5;
        first2.left = first3;
        first2.right = first4;
        first5.right = first6;
        return first1;
    }

}
