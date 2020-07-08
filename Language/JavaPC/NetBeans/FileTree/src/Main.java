
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
    public static String usage=""
            + "File tree creator with multiple Arguments.\n"
            + "Arguments:\n"
            + "  -c  --colors [Bool]\n"
            + "  -o  --output [Path]\n"
            + "  -m  --mode   [Chose From List("+Folder.getMods()+")]\n";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Settings setting=new Settings();
        if (args.length == 0) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resoult = fc.showOpenDialog(null);
            if (resoult == JFileChooser.APPROVE_OPTION) {
                Folder f = new Folder(fc.getSelectedFile());
                System.out.print(f);
            }
        } else {
            for(int i=0,len=args.length;i<len;i++){
                switch(args[i]){
                    case "-c":
                    case "--colors":
                        i++;
                        if(i<len){
                            setting.colors=Boolean.parseBoolean(args[i]);
                        }
                        break;
                    case "-o":
                    case "--output":
                        i++;
                        if(i<len){
                            setting.outputPath=args[i];
                            setting.outputFile=true;
                        }
                        break;
                    case "-m":
                    case "--mode":
                        i++;
                        if(i<len && Folder.getMods().contains(args[i])){
                            setting.Mode=args[i];
                        }
                        break; 
                    case "-h":
                    case "--help":
                        System.out.println(usage);
                        break;
                }
            }
        }
    }

}
