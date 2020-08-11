
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

    public static String usage = ""
            + "File tree creator with multiple Arguments.\n"
            + "Arguments:\n"
            + "  -c  --colors [Boolean] default True\n"
            + "  -o  --output [Path] output file\n"
            + "  -i  --input  [Path] input folder to make scan of."
            + "  -m  --mode   [Chose From List(" + Printer.getMods() + ")]\n";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Settings setting = new Settings();
        if (args.length == 0) {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resoult = fc.showOpenDialog(null);
            if (resoult == JFileChooser.APPROVE_OPTION) {
                Folder f = new Folder(fc.getSelectedFile());
                System.out.println(f.dir.getPath());
                Printer.print(setting, f);
            }
        } else {
            for (int i = 0, len = args.length; i < len; i++) {
                switch (args[i]) {
                    case "-c":
                    case "--colors":
                        i++;
                        if (i < len) {
                            setting.colors = args[i].equalsIgnoreCase("true") || args[i].equalsIgnoreCase("1");
                        } else {
                            System.out.println(usage);
                            System.exit(0);
                        }
                        break;
                    case "-o":
                    case "--output":
                        i++;
                        if (i < len) {
                            setting.outputPath = args[i];
                            setting.outputFile = true;
                        } else {
                            System.out.println(usage);
                            System.exit(0);
                        }
                        break;
                    case "-m":
                    case "--mode":
                        i++;
                        if (i < len && Printer.getMods().contains(args[i])) {
                            setting.Mode = args[i];
                        } else {
                            System.out.println(usage);
                            System.exit(0);
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
