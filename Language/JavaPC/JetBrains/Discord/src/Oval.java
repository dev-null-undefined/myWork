import java.awt.*;

public class Oval extends Shape{
	public Oval(Rectangle dimensions,Color color,boolean isSolid){
		super(color,isSolid,dimensions);
	}
	@Override
	public void draw(Graphics g){
		g.drawOval(this.x,this.y,this.width,this.height);
	}
}
