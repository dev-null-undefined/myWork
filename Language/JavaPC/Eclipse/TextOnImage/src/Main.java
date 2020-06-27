import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) throws Exception {
	    final BufferedImage image = ImageIO.read(new File("AlphaCompositeCLEAR.png"));
	    String text="hello world how are you today";
	    Graphics g = image.getGraphics();
	    g.setColor(Color.BLACK);
	    g.setFont(g.getFont().deriveFont(30f));
	    FontMetrics metrics = new FontMetrics(g.getFont().deriveFont(30f)) {};
	    Rectangle2D bounds = metrics.getStringBounds(text, null);
	    int widthInPixels = (int) bounds.getWidth();
	    g.drawString("Hello Worldsd gsasgaga g d g!", 100, 100);
	    g.dispose();

	    ImageIO.write(image, "png", new File("test.png"));
	}

}
