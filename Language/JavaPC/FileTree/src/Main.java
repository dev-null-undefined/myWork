
import javax.swing.JFileChooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marti
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFileChooser fc=new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resoult=fc.showOpenDialog(null);
        if(resoult==JFileChooser.APPROVE_OPTION){
            Folder f=new Folder(fc.getSelectedFile());
            System.out.print(f);
        }
    }
    
}
