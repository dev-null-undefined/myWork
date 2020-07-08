
import java.io.File;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marti
 */
public class Folder {
    private static LinkedList<String> avaibleMods;
    public static LinkedList<String> getMods(){
        if(avaibleMods==null){
            avaibleMods=new LinkedList<>();
//            avaibleMods.add("html");
            avaibleMods.add("text");
        }
        return avaibleMods;
    }
    private final LinkedList<Folder> subFolders=new LinkedList<>();
    private final LinkedList<File> subFiles=new LinkedList<>();
    private final File dir;
    private final String subCount;
    public Folder(File URL){
        dir=URL;
        this.inicialization();
        subCount="";
    }
    public Folder(File URL,String subCount){
        dir=URL;
        this.subCount=subCount;
        this.inicialization();
    }
    public void inicialization(){
        for (File file : dir.listFiles()) {//for each file and folder in dir
            if (file.isDirectory()) {//if is it folder
                subFolders.add(new Folder(file,(subCount.concat("|"))));
            } else {
                subFiles.add(file);
            }
        }
    }
    
    @Override
    public String toString(){
        String returnString="Folder: "+dir.getName();
        for(Folder f:subFolders){
            returnString+=subCount+"-"+f.toString();
        }
        for(File f:subFiles){
            returnString+=f.getName()+"\n";
        }
        return returnString;
    }
    public String toString(int x){
        String space=x+"";
        for(int i=0;i!=x;i++){
            space+="-.";
        }
        String returnString=space+"Folder: "+dir.getPath()+"\n";
        returnString+=space+"Have SubFolders:\n";
        for(Folder f:subFolders){
            returnString+="\n"+f.toString(x+1);
        }
        returnString+=space+"And SubFiles:\n";
        for(File f:subFiles){
            returnString+=space+f.getName()+"\n";
        }
        return returnString+"\n";
    }
}
