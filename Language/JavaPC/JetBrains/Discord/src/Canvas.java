import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.concurrent.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ListIterator;

import javax.swing.JApplet;
import javax.swing.JFrame;


public class Canvas {


    private int height;
    private int width;
    private CopyOnWriteArrayList<Triangle> triangles;

    public Canvas() {
        JFrame f = new JFrame("Canvas");
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        height = 800;
        width = 800;
        f.setSize(width, height);
//        f.getContentPane().add(this);
        triangles = new CopyOnWriteArrayList<Triangle>();
        f.setVisible(true);
    }

    public Canvas(int h, int w) {
        JFrame f = new JFrame("Canvas");
        f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        height = h;
        width = w;
        f.setSize(width, height);
//        f.getContentPane().add(this);
        triangles = new CopyOnWriteArrayList<Triangle>();
        f.setVisible(true);
    }

//    public void paint(Graphics g) {
//        Image buffer = createImage(width,height);
//        drawToBuffer(buffer.getGraphics());
//        g.drawImage(buffer,0,0,null);
//    }



    public void drawToBuffer(Graphics g){
        ListIterator<Triangle> triItr = triangles.listIterator();


        while (triItr.hasNext()) {
            Triangle curTriangle = triItr.next();
            g.setColor(curTriangle.getColor());
            Polygon po = new Polygon();
            po.addPoint((int)curTriangle.getXPos(), (int)curTriangle.getYPos());
            po.addPoint((int)(curTriangle.getXPos() + curTriangle.getWidth()), (int)curTriangle
                    .getYPos());
            po.addPoint((int)(curTriangle.getXPos() + curTriangle.getWidth() / 2), (int)(curTriangle.getYPos() - curTriangle.getHeight()));
            g.fillPolygon(po);
        }
    }



    public void drawShape(Triangle tri){
        triangles.add(tri);
    }

    public void clear(){
        triangles.clear();
    }


}
