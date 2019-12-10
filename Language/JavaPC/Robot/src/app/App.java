package app;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        Random rand=new Random();
        int x,y;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        try{//
            robot r=new robot();
          //  Thread.sleep(6000);
            for(int z=0;z!=500;z++){
                //x=rand.nextInt(((int)screenSize.getHeight()));
                //y=rand.nextInt(((int)screenSize.getWidth()));
                //r.mouseMove(x, y);
                //Thread.sleep(100);
              //  r.keyClick(KeyEvent.VK_NUMPAD9);
               // Error er=new Error();
               // er.initializeRandLoc();
            }//999
           // System.exit(0);
        }catch(Exception e){
            e.printStackTrace();            
        }

    }
}