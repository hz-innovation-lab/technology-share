package AStar寻路算法;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author baiyundou
 * @date 10:43 2019/10/18
 * @description
 */
public class MyAStar {

    private List<Node> opens = new ArrayList<>();

    private List<Node> closes = new ArrayList<>();

    private Maze maze;

    public MyAStar(Maze maze) {
        this.maze = maze;
    }

    public void search() {
        Node startNode = maze.getStartNode();
        Node endNode = maze.getEndNode();
        startNode.setPNode(startNode);
        opens.add(startNode);
        while (!opens.contains(endNode)) {
            Collections.sort(opens);
            Node node = opens.get(0);
            open(node);
            close(node);
        }
        showPath();
    }

    private void close(Node node) {
        if(opens.contains(node)){
            opens.remove(node);
            closes.add(node);
            node.setReachable(false);
        }
    }

    private void open(Node startNode) {
        int x = startNode.getX() ;
        int y = startNode.getY() ;
        Node current = null;
        //todo 先不判断0的问题
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int currentX = x - 1 + i;
                int currentY = y - 1 + j;
                current = maze.getMap()[currentX][currentY];
                if (current == startNode || opens.contains(current) || !current.isReachable()) {
                    //do nothing
                }else{
                    opens.add(current);
                    current.setPNode(startNode);
                    //F = G + H
                    current.setGValue(calGValue(current));
                    current.setHValue(calHValue(current));
                }
            }
        }
    }

    private double calHValue(Node current) {
        //曼哈顿距离估算
        return Math.abs(current.getX() - maze.getEndNode().getX()) + Math.abs(current.getY() - maze.getEndNode().getY());
    }

    private double calGValue(Node current) {
        Node pNode = current.getPNode();
        if (pNode.getX() == current.getX() || pNode.getY() == current.getY()) {
            return calHValue(current.getPNode()) + 10;
        } else {
            return calHValue(current.getPNode()) + 14;
        }
    }

    private void showPath() {
        if(closes.size() != 0){
            Node endNode = maze.getEndNode();
            while (endNode != maze.getStartNode()){
                endNode = endNode.getPNode();
                endNode.setValue("*");
            }
            maze.getStartNode().setValue("A");
        }
    }

}
