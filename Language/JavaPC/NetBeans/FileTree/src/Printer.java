
import java.io.File;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author martin
 */
public class Printer {
    
    private static LinkedList<String> avaibleMods;
    public static LinkedList<String> getMods(){
        if(avaibleMods==null){
            avaibleMods=new LinkedList<>();
            avaibleMods.add("text");
        }
        return avaibleMods;
    }

    public static void print(Settings settings, Folder root) {
        if (!settings.outputFile) {
            for (int counter = 0, len = root.subFolders.size(); counter < len; counter++) {
                Folder current = root.subFolders.get(counter);
                String toPrint = "";
                if (counter == len - 1 && root.subFiles.isEmpty()) {
                    toPrint += "╘";
                } else {
                    toPrint += "╞";
                }
                toPrint += "══" + current.dir.getName();
                while (current.parrent.parrent != null) {
                    current = current.parrent;
                    if (current.parrent.subFolders.indexOf(current) == current.parrent.subFolders.size() - 1 && current.parrent.subFiles.size() == 0) {
                        toPrint = "   " + toPrint;
                    } else {
                        toPrint = "│  " + toPrint;
                    }
                }
                System.out.println(toPrint);
                print(settings, root.subFolders.get(counter));
            }
            for (int counter = 0, len = root.subFiles.size(); counter < len; counter++) {
                File current = root.subFiles.get(counter);
                String toPrint = "";
                if (counter == len - 1) {
                    toPrint += "└";
                } else {
                    toPrint += "├";
                }
                toPrint += "──" + current.getName();
                Folder currentFolder = root;
                while (currentFolder.parrent != null) {
                    if (currentFolder.parrent.subFolders.indexOf(currentFolder) == currentFolder.parrent.subFolders.size() - 1 && currentFolder.parrent.subFiles.size() == 0) {
                        toPrint = "   " + toPrint;
                    } else {
                        toPrint = "│  " + toPrint;
                    }
                    currentFolder = currentFolder.parrent;
                }
                System.out.println(toPrint);
            }
        } else {

            //    folder ═ ═  last folder and no file ╘ else ╞
            //    file ─ ─ last file └ else ├
            //    last folder │ _ _ else _ _ _
        }
    }
}
