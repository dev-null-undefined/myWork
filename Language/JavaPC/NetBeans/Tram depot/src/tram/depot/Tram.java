/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tram.depot;

import java.util.Objects;

/**
 *
 * @author Martin
 */
public class Tram {
    private final int tramID;
    private int quality;
    private int tramNumber;
    private Driver driver;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.tramID;
        return hash;
    }
    
    @Override
    public String toString() {
        return "Tram with ID: " + tramID + ", quality: " + State.intToString(quality).toLowerCase()
                + ", tramNumber: " + tramNumber + (driver!=null?(", drived by " + driver.toString()+"."):(", without driver"));
    }
    
    public boolean isFree(){
        return driver==null;
        
    }
    public Tram(int ID,int quality,int tramNumber,Driver driver){
        this.tramID=ID;
        this.quality=quality;
        this.tramNumber=tramNumber;
        this.driver=driver;
    }
    
    /**
     *
     * @param E
     * @return
     */
    @Override
    public boolean equals(Object E){
        if(E==null)
            return false;
        try{
            Tram e=(Tram) E;
            return e.getTramID()==tramID;
        }catch(Exception F){
            return false;
        }        
        
    }
    
     public int getTramID(){
        return tramID;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void setTramNumber(int tramNumber) {
        this.tramNumber = tramNumber;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public int getTramNumber() {
        return tramNumber;
    }

    public Driver getDriver() {
        return driver;
    }
    
    
}
