package 图;

import java.util.ArrayDeque;
import java.util.Queue;

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

        bfs(0);

//        dfs(0);
        //0代表点1
    }

    //深度优先遍历
    private static void dfs(int v) {
        //v是代表是从哪个节点开始遍历
        System.out.println(v + 1);
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
            System.out.println(poll + 1);
            for (int i = 0; i < 9; i++) {
                if(!travsed[i] && w[poll][i] != Integer.MAX_VALUE){
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
        w[1 - 1][2 - 1] = 1;
        w[1 - 1][3 - 1] = 1;
        w[2 - 1][1 - 1] = 1;
        w[2 - 1][4 - 1] = 1;
        w[2 - 1][5 - 1] = 1;
        w[2 - 1][8 - 1] = 1;
        w[3 - 1][1 - 1] = 1;
        w[3 - 1][5 - 1] = 1;
        w[3 - 1][6 - 1] = 1;
        w[4 - 1][2 - 1] = 1;
        w[4 - 1][8 - 1] = 1;
        w[4 - 1][9 - 1] = 1;
        w[5 - 1][2 - 1] = 1;
        w[5 - 1][3 - 1] = 1;
        w[5 - 1][7 - 1] = 1;
        w[5 - 1][9 - 1] = 1;
        w[6 - 1][3 - 1] = 1;
        w[6 - 1][7 - 1] = 1;
        w[6 - 1][9 - 1] = 1;
        w[7 - 1][5 - 1] = 1;
        w[7 - 1][6 - 1] = 1;
        w[7 - 1][9 - 1] = 1;
        w[8 - 1][2 - 1] = 1;
        w[8 - 1][4 - 1] = 1;
        w[8 - 1][9 - 1] = 1;
        w[9 - 1][4 - 1] = 1;
        w[9 - 1][5 - 1] = 1;
        w[9 - 1][6 - 1] = 1;
        w[9 - 1][7 - 1] = 1;
        w[9 - 1][8 - 1] = 1;
    }
}
