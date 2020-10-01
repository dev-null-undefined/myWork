import java.awt.*;

abstract class Shape extends Rectangle  {

	private Color shapeColor;
	private boolean isSolid;
	private static int shapesCreated = 0;

	public Shape(Color shapeColor, boolean isSolid, Rectangle dimension) {
		super(dimension);
		this.shapeColor = shapeColor;
		this.isSolid = isSolid;
		shapesCreated++;
	}

	public Color getShapeColor() {
		return shapeColor;
	}

	public void setColor(Graphics g) {
		g.setColor(this.shapeColor);
	}

	public boolean getSolid() {
		return isSolid;
	}

	public boolean getSolid(boolean solidOrHollow) {
		return solidOrHollow;
	}

	public static int getNoOfShapes() {
		return shapesCreated;
	}

	public abstract void draw(Graphics g);

}
