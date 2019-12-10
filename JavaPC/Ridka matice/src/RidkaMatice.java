
import java.util.HashMap;
import java.util.Set;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Martin
 * @param <E>
 */
public class RidkaMatice<E> {
    private final HashMap<Integer,HashMap<Integer,E>> DoubleHashMap=new HashMap<>();
    private int returnValue=0;
    
    /**
     * add
     * @param thatOne
     * @param y
     * @param x
     * @return 
     */
    public E add(E thatOne,int x,int y){
        HashMap<Integer,E> putIn;
        E Zaloha;
        if(DoubleHashMap.get(x)!=null){
            putIn=DoubleHashMap.get(x);
            Zaloha=putIn.put(y, thatOne);
        }else{
            putIn=new HashMap<>();
            Zaloha=putIn.put(y,thatOne);
        }
        HashMap<Integer,E> zaloha = DoubleHashMap.put(x,putIn);
        if(zaloha!=null)
            return Zaloha;
        return null;
    }
    
    /**
     * is in
     * @param thatOne
     * @return 
     */
    public boolean isIn(E thatOne){
        return DoubleHashMap.keySet().stream().anyMatch((indexFromKeySetX) -> (DoubleHashMap.get(indexFromKeySetX).containsValue(thatOne)));
    }
    
    /**
     * what is there?
     * @param thatOne
     * @return 
     */
    public Coordinates whereIsIt(E thatOne){
        Set<Integer> xKeySet=DoubleHashMap.keySet();
        for(Integer indexFromKeySetX: xKeySet){
            Set<Integer> yKeySet=DoubleHashMap.get(indexFromKeySetX).keySet();
            for(Integer indexFromKeySetY: yKeySet){
                if(DoubleHashMap.get(indexFromKeySetX).get(indexFromKeySetY)!=null ? DoubleHashMap.get(indexFromKeySetX).get(indexFromKeySetY).equals(thatOne):false)
                    return new Coordinates(indexFromKeySetX,indexFromKeySetY);
            }
        }    
        return null;
    }
    /**
     * get something on
     * @param x
     * @param y
     * @return 
     */
    public E get(int x,int y){
        return DoubleHashMap.get(x).get(y);
    }
    /**
     * How many of these are there?
     * @param thatOne
     * @return 
     */
    public int pocet(E thatOne){
        returnValue=0;
        DoubleHashMap.keySet().stream().forEach(x -> DoubleHashMap.get(x).keySet().stream().forEach(y->
            {if(DoubleHashMap.get(x).get(y).equals(thatOne)){
                this.returnValue++;
            }}));
        return returnValue;
    }
    /**
     * ToString
     * @return 
     */
    @Override
    public String toString(){
        String returnString="";
        for(Integer x:DoubleHashMap.keySet()){
            returnString = DoubleHashMap.get(x).keySet().stream().map((y) -> DoubleHashMap.get(x).get(y)!=null ? "\nObject: "+DoubleHashMap.get(x).get(y)+", is on X:"+x+", and Y:"+y+".":"").reduce(returnString, String::concat);
        }
        return returnString;
    }
    /**
     * Get All of E
     * @param thatOne
     * @return 
     */
    public HashMap<Coordinates,E> getAll(E thatOne){
        HashMap<Coordinates,E> map=new HashMap<>();
        DoubleHashMap.keySet().stream()
                .forEach(x->{DoubleHashMap.get(x).keySet().stream()
                .forEach(y->{if(DoubleHashMap.get(x).get(y).equals(thatOne)){map.put(new Coordinates(x, y), DoubleHashMap.get(x).get(y));
                }});});
        return map;
        
    }
    /**
     * remove
     * @param x
     * @param y
     * @return 
     */
    public E remove(int x,int y){
        return this.add(null, x, y);
    }
    /**
     * remove with some E
     * @param thatOne
     * @return 
     */
    public E remove(E thatOne){
        Coordinates loc=this.whereIsIt(thatOne);
        return this.remove(loc.getX(),loc.getY());
    }
    
    /**
     * clear
     * @return 
     */
    public RidkaMatice clear(){
        RidkaMatice returnZaloha=this;
        DoubleHashMap.clear();
        return returnZaloha;
    }
    
    /**
     * add all
     * @param add
     */
    public void addAll(RidkaMatice<E> add){
        HashMap<Integer,HashMap<Integer,E>> b=add.getMap();
        b.keySet().forEach((x) -> {
            b.get(x).keySet().stream().filter((y) -> (b.get(x).get(y)!=null)).forEachOrdered((y) -> {
                this.add(b.get(x).get(y), x, y);
            });
        });   
    }
    
    /**
     * Get map
     */
    private HashMap getMap(){
        return DoubleHashMap;
    }
    
    
}
