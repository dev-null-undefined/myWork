
import java.util.ArrayList;

public class App {

    public static void main(String[] args) throws Exception {
        /*
         * Maze maze = new Maze(new int[][]{ {1, 1, 1, 1, 3, 1}, {1, 0, 0, 0, 0, 1}, {1,
         * 0, 1, 1, 0, 1}, {1, 0, 0, 0, 0, 1}, {1, 1, 1, 4, 1, 1},});
         */
        Maze maze = new Maze(10, 20);
        // maze.setEnd(new Coordinates(0, 4));
        // maze.setStart(new Coordinates(4, 3));
        // maze.addPlayer(new Player(new Coordinates(1, 2), "Karel-Bot", true));
        // maze.addPlayer(new Player(new Coordinates(3, 2), "Karel", false));
        maze.build(3);
        System.out.println(maze);
        // System.out.println(maze.getEnd() + "," + maze.getStart());
        ArrayList<Coordinates> a = maze.getPatchOut();
        System.out.println(maze.toStringWithPatch(a) + "\n" + maze.getCount());

    }
}
