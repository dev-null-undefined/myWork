package app;

import java.util.ArrayList;
import java.util.Random;

public class Maze {
    private int[][] grid;

    public Maze(int Size) {
        grid = new int[Size][Size];
    }

    public Maze(int SizeX, int SizeY) {
        grid = new int[SizeX][SizeY];
    }

    public void build() {

    }

    public void build(int level) {
        Random r = new Random();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (x == 0 || y == 0 || x == grid.length - 1 || y == grid[x].length - 1) {
                    grid[x][y] = 1;
                } else {
                    if (r.nextInt(level) == 0) {
                        grid[x][y] = 0;
                    } else {
                        grid[x][y] = 2;
                    }
                }
            }
        }
        /*
         * if (getPatchFromTo() == null) { build(level); } else { return; }
         */
    }

    public int[][] getGrid() {
        return grid;
    }

    /*
     * public ArrayListt<Coordinates> getPatchFromTo(Coordinates from, Coordinates
     * to) {
     * 
     * }
     * 
     * public ArrayListt<Coordinates> getPatchOut(Coordinates loc) { return
     * getPatchFromTo(loc, getExit()); }
     * 
     * public Coordinates getExit() {
     * 
     * }
     */
}