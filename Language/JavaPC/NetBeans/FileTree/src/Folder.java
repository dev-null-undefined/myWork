
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
    public final LinkedList<Folder> subFolders = new LinkedList<>();
    public final LinkedList<File> subFiles = new LinkedList<>();
    public final File dir;
    public final Folder parrent;
    
    public Folder(File dir){
        this.dir = dir;
        parrent = null;
        this.scanDirAndFill(this);
    }

    public Folder(File dir, Folder parrent) {
        this.dir = dir;
        this.parrent = parrent;
        this.scanDirAndFill(this);
    }

    public static void scanDirAndFill(Folder folder) {
        for (File file : folder.dir.listFiles()) {//for each file and folder in dir
            if (file.isDirectory()) {//if is it folder
                folder.subFolders.add(new Folder(file, folder));
            } else {
                folder.subFiles.add(file);
            }
        }
    }
}
