package 二叉树;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

/**
 * @author baiyundou
 * @date 17:02 2019/10/11
 * @description
 */
public class Travse {

    public static void main(String[] args) {
        TreeNode treeNode = constructTreeNode();
        //前序遍历
//        preTravse(treeNode);
        //中序遍历
//        middleTravse(treeNode);
        //后序遍历
//        postTravse(treeNode);
//
        //层序遍历 bfs 广度优先遍历
//        floorTravse(treeNode);
        //dfs 深度优先遍历(其实和前序遍历一样)
        deepTravse(treeNode);
    }

    //中序遍历
    private static void middleTravse(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack();
        stack.push(treeNode);
        HashMap<TreeNode, Integer> map = new HashMap<>();
        while (stack.size() != 0) {
            TreeNode pop = stack.pop();
            if (map.get(pop) != null) {
                System.out.println(pop.val);
            } else {
                map.put(pop, 1);
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                stack.push(pop);
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            }
        }
    }

    private static void postTravse(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack();
        stack.push(treeNode);
        HashMap<TreeNode, Integer> map = new HashMap<>();
        while (stack.size() != 0) {
            TreeNode pop = stack.pop();
            if (map.get(pop) != null) {
                System.out.println(pop.val);
            } else {
                map.put(pop, 1);
                stack.push(pop);
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            }
        }
    }

    //前序遍历
    private static void preTravse(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack();
        stack.push(treeNode);
        while (stack.size() != 0) {
            TreeNode pop = stack.pop();
            System.out.println(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    //层序遍历
    private static void floorTravse(TreeNode treeNode) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(treeNode);
        while (queue.size() != 0) {
            TreeNode remove = queue.poll();
            System.out.println(remove.val);
            if (remove.left != null) {
                queue.add(remove.left);
            }
            if (remove.right != null) {
                queue.add(remove.right);
            }
        }
    }

    //深度优先遍历--栈实现 二叉树的深度优先其实就是先序遍历
    private static void deepTravse(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(treeNode);
        while (stack.size() != 0) {
            TreeNode pop = stack.pop();
            System.out.println(pop.val);
            if(pop.right != null){
                stack.push(pop.right);
            }
            if(pop.left != null){
                stack.push(pop.left);
            }
        }
    }

    /**

                     1
          2                     5
      3     4                      6

    **/
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
