/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public class Coordinates {
    
    private final int x,y;
    public Coordinates(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    @Override
    public String toString(){
        return " x=" + x + ", y= " + y + ";";
    }
    
     
        
    
}
