package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Error extends JFrame{
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int sizeX=400,sizeY=250;
    private static final long serialVersionUID = 1L;
    private static final Random r=new Random();
    public void initialize(){
        this.setLayout(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton jb=new JButton("Ok");
        jb.setBounds(150, 125, 100, 75);
        this.setResizable(false);
        this.add(jb);
        this.setBounds(((((int)screenSize.getWidth())/2)-sizeX), ((((int)screenSize.getHeight())/2)-sizeY), sizeX, sizeY);
        JTextArea jt=new JTextArea("!!ERROR!!");
        jt.setBounds(50, 10, 300, 50);
        jt.setBackground(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
        this.setBackground(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
        jt.setEnabled(false);
        this.add(jt);
        this.repaint();
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
    }
    public void initializeRandLoc(){
        this.setLayout(null);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton jb=new JButton("Ok");
        jb.setBounds(150, 125, 100, 75);
        this.setResizable(false);
        this.setBackground(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
        this.add(jb);
        int y=r.nextInt(((int)screenSize.getHeight())-sizeY);
        int x=r.nextInt(((int)screenSize.getWidth())-sizeX);
        this.setBounds(x, y, sizeX, sizeY);
        JTextArea jt=new JTextArea("!!ERROR!!");
        jt.setBounds(50, 10, 300, 50);
        jt.setBackground(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
        jt.setEnabled(false);
        this.add(jt);
        this.repaint();
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
    }
    
}