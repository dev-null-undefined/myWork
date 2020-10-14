import java.awt.*;

public class Triangle {
    private double xPos;
    private double yPos;
    private double width ;
    private double height ;
    private Color color ;
    public Triangle(double xPos, double yPos, double width, double height) {

        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.color = Color.BLACK;
    }
    public double calculatePerimeter() {
        return 3 * this.width ;
    }
    public double calculateArea() {
        return 0.5 * this.width * this.height ;
    }
    public double getXPos() {
        return xPos;
    }
    public void setXPos(double xPos) {
        this.xPos = xPos;
    }
    public double getYPos() {
        return yPos;
    }
    public void setYPos(double yPos) {
        this.yPos = yPos;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }


}
