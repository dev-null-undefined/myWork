/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tram.depot;

import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Martin
 */
public class TramDatabase {
    HashSet<Tram> collection=new HashSet<>();
    /*
    which adds the tram to the register,
    and only if there is no tram with the same car number in the list,
    the method returns true if the tram was inserted into the list
    and false if it was already in the list.
    */
    public boolean addTram(Tram add){
        return collection.add(add); //HashSet.add(E) allready returning boolean if it was added.
    }
    
    /*
    Returns the list of all trams from the records,
    method returns always list, even empty, never null !!
    */
    public Collection<Tram> getCollection(){
        return collection;
    }
    
    /*
    Returns a list of trams from records that are not taken (driver is null),
    if there is no free tram in the list, returns empty list.
    */
    public Collection<Tram> getFreeTrams(){
        HashSet<Tram> returnHashSet=new HashSet<>();
        collection.stream().filter(x->x.isFree()).forEach(y->returnHashSet.add(y));
        return returnHashSet;
    }
    
    private Tram returnTram;
    public Tram getTram(int tramID){
        collection.stream().filter(x->x.getTramID()==tramID).forEach(z->returnTram=z);
        return returnTram;
    }

    private String trams="\n";
    @Override
    public String toString() {
        collection.stream().forEach(x->trams+=x.toString()+"\n");
        return "TramDatabase: " + trams;
    }
    
    
}
