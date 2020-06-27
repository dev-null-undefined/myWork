
public class Coordinates {
    private int x, y;

    @Override
    public String toString(){
        return ("Coordinates x:"+x+", y:"+y);
    }
    @Override
    public boolean equals(Object o){
        Coordinates c=(Coordinates) o;
        return c.getX()==x&&c.getY()==y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.x;
        hash = 59 * hash + this.y;
        return hash;
    }
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