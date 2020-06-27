package app;

import java.awt.AWTException;
import java.awt.Robot;

public class robot extends Robot {
    public robot() throws AWTException {
        super();
    }
    public void keyClick(int key) {
        this.keyPress(key);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.keyRelease(key);
    }
}