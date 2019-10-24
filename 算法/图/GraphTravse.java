package 图;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * @author baiyundou
 * @date 18:47 2019/10/15
 * @description
 */
public class GraphTravse {

    private static int[][] w;

    private static int len;

    private static boolean[] travsed = new boolean[9];

    public static void main(String[] args) {

        createGraph();

//        bfs(0);

        dfs(0);
        //0代表点1

//        dfsStack(0);
    }

    private static void travseNode(int v){
        //打印node
        System.out.println(v);
        //标记已遍历
        travsed[v] = true;
    }
    private static void dfsStack(int v) {
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        travseNode(v);
        while (stack.size() != 0) {
            Integer peek = stack.peek();
            boolean flag = false;
            for (int i = 0; i < w.length; i++) {
                if (w[peek][i] == 1) {
                    //有边
                    if (!travsed[i]) {
                        //且节点未被遍历
                        stack.push(i);
                        travseNode(i);
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                //所有连接的点均已被遍历，直接pop当前节点即可
                stack.pop();
            }
        }
    }

    //深度优先遍历
    private static void dfs(int v) {
        //v是代表是从哪个节点开始遍历
        System.out.println(v);
        travsed[v] = true;
        for (int i = 0; i < 9; i++) {
            if (!travsed[i] && w[v][i] != Integer.MAX_VALUE) {
                dfs(i);
            }
        }
    }

    //广度优先遍历
    private static void bfs(int v) {
        //v是代表是从哪个节点开始遍历
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(v);
        while (queue.size() != 0) {
            Integer poll = queue.poll();
            travsed[poll] = true;
            System.out.println(poll);
            for (int i = 0; i < 9; i++) {
                if (!travsed[i] && w[poll][i] != Integer.MAX_VALUE) {
                    travsed[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    private static void createGraph() {
        w = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == j) {
                    w[i][j] = 0;
                } else {
                    w[i][j] = Integer.MAX_VALUE;
                    //用MAX_VALUE来代表边不连通
                }
            }
        }
        //权重,全设置为1
        len = 9;
        w[0][1] = 1;
        w[0][2] = 1;
        w[1][0] = 1;
        w[1][3] = 1;
        w[1][4] = 1;
        w[1][7] = 1;
        w[2][0] = 1;
        w[2][4] = 1;
        w[2][5] = 1;
        w[3][1] = 1;
        w[3][7] = 1;
        w[3][8] = 1;
        w[4][1] = 1;
        w[4][2] = 1;
        w[4][6] = 1;
        w[4][8] = 1;
        w[5][2] = 1;
        w[5][6] = 1;
        w[5][8] = 1;
        w[6][4] = 1;
        w[6][5] = 1;
        w[6][8] = 1;
        w[7][1] = 1;
        w[7][3] = 1;
        w[7][8] = 1;
        w[8][3] = 1;
        w[8][4] = 1;
        w[8][5] = 1;
        w[8][6] = 1;
        w[8][7] = 1;
    }
}
