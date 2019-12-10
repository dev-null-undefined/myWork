/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesorterbydatatype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Martin
 */
public class FilesInFolder implements Iterable<String> {

    private final HashMap<String, ArrayList<String>> reverseMap;
    private final HashMap<String, String> array;//hash map with K-data type

    @Override
    public Iterator<String> iterator() {
        return reverseMap.keySet().iterator();
    }
    
    public ArrayList<String> getFromReverseMap(String Key){
        return reverseMap.get(Key);
    }

    public FilesInFolder() {
        this.reverseMap = new HashMap<>();
        array = new HashMap<>();
    }
    //and E - folder name

    /**
     * Contains in HashMap ignore case
     *
     * @param s String
     * @return folder name for s
     */
    public String contains(String s) {
        return array.get(s.toLowerCase());
    }
    
    public boolean secoundContains(String s){
        return array.containsValue(s);
    }
    /**
     * Add extention for folder
     *
     * @param data
     * @param folder
     */
    public void addDataTypeIntoFolder(String data, String folder) {
        array.put(data.toLowerCase(), folder);
        if (reverseMap.containsKey(folder)) { //adding it into revers map to for better toString
            reverseMap.get(folder).add(data);
        } else {
            ArrayList<String> addToReverseMap = new ArrayList<>();
            addToReverseMap.add(data);
            reverseMap.put(folder, addToReverseMap);
        }
    }

    @Override
    public String toString() {
        String toString = "";
        int y = 0;
        for (String k : reverseMap.keySet()) {
            toString += "Folder " + k + " have these data types in: ";
            y = 0;
            for (String x : reverseMap.get(k)) {
                y++;
                if (y % 5 == 0) {
                    toString += "\n";
                }
                toString += x + ",";
            }
            toString += "\n";
        }
        return toString;
    }

    /*  public String secoundaryToString() {
        String toString="";
        for(String k:array.keySet()){
            toString+="File extention "+k+" is in folder "+array.get(k)+"\n";
        }
        return toString;
    }*/
}
