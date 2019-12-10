/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testb;

import java.util.LinkedList;

/**
 *
 * @author marti
 */
public class Zahradnictvi {
    private LinkedList<Rostlina> prvky=new LinkedList<>();

    public void addPrvek(Rostlina a){
        prvky.add(a);
    }
    public boolean removePrvek(Rostlina a){
        return prvky.remove(a);
    }
    public LinkedList<Rostlina> fromTo(long min,long max){
        LinkedList<Rostlina> vrat=new LinkedList<>();
        for(Rostlina a:prvky){
            if(a.getCena()>=min&&a.getCena()<=max){
                vrat.add(a);
            }
        }
        return vrat;
    } 

    @Override
    public String toString() {
        return "Zahradnictvi{" + "prvky=" + prvky + '}';
    }
    
    
}
