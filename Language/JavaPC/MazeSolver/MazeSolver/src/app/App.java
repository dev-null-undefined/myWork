package app;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("H");
        Maze maze = new Maze(10);
        maze.build(1);
        System.out.println("H");
    }
}