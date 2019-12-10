
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;

public class Maze {

    private int count = 0;
    private long time;

    private void resetCount() {
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    private int[][] grid;
    private Coordinates start;
    private Coordinates end;
    private final HashSet<Player> players = new HashSet<>();

    public HashSet<Player> getPlayers() {
        return players;
    }

    public boolean addPlayer(Player player) {
        return this.players.add(player);
    }

    public Maze(int Size) {
        time = System.nanoTime();
        if (Size < 3) {
            throw new RuntimeException("Size must be number bigger then 3.");
        }
        grid = new int[Size][Size];
    }

    public Maze(int[][] gridL) {
        time = System.nanoTime();
        grid = gridL;
    }

    public Maze(int SizeX, int SizeY) {
        time = System.nanoTime();
        grid = new int[SizeX][SizeY];
    }

    public void build() {
        this.build(4);
    }

    private int x;
    private int y;

    public void build(int level) {
        if (level < 0) {
            throw new RuntimeException("Level must be positive number.");
        }
        Random r = new Random();
        for (x = 0; x < grid.length; x++) {
            for (y = 0; y < grid[x].length; y++) {
                Optional<Player> p = players.stream()
                        .filter(o -> (o.getPosition().getX() == x && o.getPosition().getY() == y)).findAny();
                if (p.isPresent()) {
                    if (p.get().isIsBot()) {
                        grid[x][y] = 7;
                    } else {
                        grid[x][y] = 6;
                    }
                } else {
                    if (x == 0 || y == 0 || x == grid.length - 1 || y == grid[x].length - 1) {
                        grid[x][y] = 1;
                    } else {
                        if (this.chanceForWall(level)) {
                            grid[x][y] = 0;
                        } else {
                            grid[x][y] = 2;
                        }
                    }
                }
            }
        }

        /*
         * Random random = new Random(); int randomPos = random.nextInt(grid[0].length -
         * 2) + 1; grid[0][randomPos] = 3; start = new Coordinates(0, randomPos);
         * randomPos = random.nextInt(grid[0].length - 2) + 1; grid[grid.length -
         * 1][randomPos] = 4; end = new Coordinates(grid.length - 1, randomPos);
         */

        // Testing if maze can be solved

        start = new Coordinates(0, 1);
        grid[0][1] = 3;
        grid[grid.length - 1][grid[0].length - 2] = 4;
        end = new Coordinates(grid.length - 1, grid[0].length - 2);
        if (count % 1000 == 0) {
            long dif = System.nanoTime() - time;
            time = System.nanoTime();
            System.out.println("XD" + count + "," + dif / 1000000000.0 + "s");
        } /*
           * else{ System.out.print("1"); }
           */
        /*
         * if (getPatchOut().isEmpty()) { count++; build(level); }
         */

        players.stream().forEach(x -> reset = (this.getPatchOut(x.getPosition()).isEmpty() == false) && reset);
        if (!reset) {
            count++;
            build(level);
        }
    }

    private boolean reset = true;

    public boolean chanceForWall(int level) {
        double r = Math.random();
        double localX = Math.sqrt(Math.sqrt(level / 2.0) / 2.0) / 2.0;
        return localX < r;
    }

    public int[][] getGrid() {
        return grid;
    }

    public Coordinates getStart() {
        return start;
    }

    public Coordinates getEnd() {
        return end;
    }

    public boolean setStart(Coordinates start) {
        if (start.getX() >= 0 && start.getX() < grid.length && start.getY() >= 0 && start.getY() < grid[0].length) {
            grid[this.start.getX()][this.start.getY()] = 1;
            this.start = start;
            grid[start.getX()][start.getY()] = 3;
            return true;
        }
        return false;
    }

    public boolean setEnd(Coordinates end) {
        if (end.getX() >= 0 && end.getX() < grid.length && end.getY() >= 0 && end.getY() < grid[0].length) {
            grid[this.end.getX()][this.end.getY()] = 1;
            this.end = end;
            grid[end.getX()][end.getY()] = 4;
            return true;
        }
        return false;
    }

    public String translate(int x) {
        switch (x) {
        case 0:
            return ".";
        case 1:
            return "Z";
        case 2:
            return "X";
        case 3:
            return "S";
        case 4:
            return "E";
        case 5:
            return "O";
        case 6:
            return "P";
        case 7:
            return "B";
        default:
            return "Error";
        }
    }

    @Override
    public String toString() {
        String resoult = "";
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                resoult += translate(grid[x][y]) + ",";
            }
            resoult += "\n";
        }
        return resoult;
    }

    public String toStringWithPatch(ArrayList<Coordinates> a) {
        for (Coordinates s : a) {
            if (s.getX() < grid.length && s.getY() < grid[0].length) {
                grid[s.getX()][s.getY()] = 5;
            }
        }
        String resoult = "";
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                resoult += translate(grid[x][y]) + ",";
            }
            resoult += "\n";
        }
        return resoult;
    }

    public ArrayList<Coordinates> getPatchFromTo(Coordinates from, Coordinates to) {
        return getPatchFromToPrivate(from, to, new ArrayList<>());
    }

    private ArrayList<Coordinates> getPatchFromToPrivate(Coordinates from, Coordinates to,
            ArrayList<Coordinates> beenTo) {

        ArrayList<ArrayList<Coordinates>> someArr = new ArrayList<>();
        ArrayList<Coordinates> save = beenTo, r, bla;
        save.add(from);
        if (from.equals(to)) {
            return beenTo;
        }
        if (from.getX() - 1 >= 0 && !save.contains(new Coordinates(from.getX() - 1, from.getY()))
                && grid[from.getX() - 1][from.getY()] != 1 && grid[from.getX() - 1][from.getY()] != 2) {
            r = getPatchFromToPrivate(new Coordinates(from.getX() - 1, from.getY()), to, new ArrayList<>(save));

            if (r.isEmpty() == false) {
                someArr.add(r);
            }
        }
        save = beenTo;
        if (from.getY() + 1 < grid[from.getX()].length && !save.contains(new Coordinates(from.getX(), from.getY() + 1))
                && grid[from.getX()][from.getY() + 1] != 1 && grid[from.getX()][from.getY() + 1] != 2) {
            r = getPatchFromToPrivate(new Coordinates(from.getX(), from.getY() + 1), to, new ArrayList<>(save));
            if (r.isEmpty() == false) {
                someArr.add(r);
            }
        }
        save = beenTo;
        if (from.getX() + 1 < grid.length && !save.contains(new Coordinates(from.getX() + 1, from.getY()))
                && grid[from.getX() + 1][from.getY()] != 1 && grid[from.getX() + 1][from.getY()] != 2) {
            r = getPatchFromToPrivate(new Coordinates(from.getX() + 1, from.getY()), to, new ArrayList<>(save));

            if (r.isEmpty() == false) {
                someArr.add(r);
            }
        }

        save = beenTo;
        if (from.getY() - 1 >= 0 && !save.contains(new Coordinates(from.getX(), from.getY() - 1))
                && grid[from.getX()][from.getY() - 1] != 1 && grid[from.getX()][from.getY() - 1] != 2) {
            r = getPatchFromToPrivate(new Coordinates(from.getX(), from.getY() - 1), to, new ArrayList<>(save));
            if (r.isEmpty() == false) {
                someArr.add(r);
            }
        }
        bla = new ArrayList<>();
        int steps = Integer.MAX_VALUE;
        for (ArrayList<Coordinates> forEach : someArr) {
            if (forEach.size() < steps && forEach.isEmpty() != true) {
                bla = forEach;
                steps = forEach.size();
            }
        }
        return bla;
    }

    public ArrayList<Coordinates> getPatchOut(Coordinates loc) {
        return getPatchFromTo(loc, end);
    }

    public ArrayList<Coordinates> getPatchOut() {
        return getPatchFromTo(start, end);
    }

}
