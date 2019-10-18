package AStar寻路算法;

/**
 * @author baiyundou
 * @date 18:14 2019/10/17
 * @description
 */
public class MainTest {
    public static void main(String[] args) {
        Maze maze = new Maze();
        MyAStar aStar = new MyAStar(maze);
        maze.ShowMap();
        aStar.search();
        System.out.println("=============================");
        System.out.println("经过A*算法计算后");
        System.out.println("=============================");
        maze.ShowMap();
    }

}
