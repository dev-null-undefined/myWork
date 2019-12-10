package app;

public class Coordinates {
    private int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int setX(int x) {
        int retX = this.x;
        this.x = x;
        return retX;
    }

    public int setY(int y) {
        int retY = this.y;
        this.y = y;
        return retY;
    }

}