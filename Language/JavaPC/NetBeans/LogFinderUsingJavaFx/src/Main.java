
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marti
 */
public class Main extends Application {

    private static final JTextArea console = new JTextArea();
    private static JScrollPane scrol;
    private static JButton stopButton;
    private static JButton redoButton;
    public static String fileFilter = "";
    public static Date dateFromFilter = null, dateToFilter = null;
    private static List<File> f;

    public static void main(String[] args) {
        launch(args);
    }

    public static void getFilters() {
        FilterForm form = new FilterForm();
    }

    public static String arrayToString(List<File> lines) {
        String returnString = "";
        for (File f : lines) {
            returnString += f.getAbsolutePath() + System.lineSeparator();
        }
        return returnString;
    }

    public static void initializate() {
        Main.makeOutPut();
        /*LinkedList<File> files = new LinkedList<>();
        System.out.println(dateFromFilter + "," + dateToFilter + "," + fileFilter);

        for (File file : f) {
            if (file.isDirectory()) {
                files.addAll(Main.getFilesFromDir(file));
            }
            if (file.isFile()) {
                files.push(file);
            }
        }*/
        console.append("Loaded all of these files: " + System.lineSeparator() + Main.arrayToString(f));
        console.append(System.lineSeparator());
        LinkedList<String> lines = new LinkedList<>();
        for (File fileInFiles : f) {
            try (BufferedReader fr = new BufferedReader(new FileReader(fileInFiles))) {
                boolean continu = true;
                do {
                    String line = fr.readLine();
                    if (line != null) {
                        lines.push(line);
                    } else {
                        continu = false;
                    }
                } while (continu);

            } catch (Exception e) {
                console.append("!Some thing went wrong when reading file: " + fileInFiles.getAbsolutePath() + "; Exception: " + e.getMessage() + " !");
            }
        }
        lines = filter(lines, dateFromFilter, dateToFilter, fileFilter);
        consoleLinkedList(lines);
        console.append("Amount: " + lines.size() + System.lineSeparator());
        HashMap<String, Integer> Ips = new HashMap<>();
        for (String line : lines) {
            String help = line.split(" - - ")[0];
            if (Ips.containsKey(help)) {
                Ips.replace(help, Ips.get(help) + 1);
            } else {
                Ips.put(help, new Integer(1));
            }
        }
        console.append("Unique ips count: " + Ips.size() + System.lineSeparator());
        console.append("Ips with Count: " + System.lineSeparator());
        console.append(hashMapToString(Ips));
        console.append("Unique Ips List: " + System.lineSeparator());
        console.append(Ips.keySet().stream().reduce((x, y) -> String.format("%s,%s", x, y)).get() + System.lineSeparator());

    }

    public static String hashMapToString(HashMap<String, Integer> hashMap) {
        String returnString = "";
        for (Object key : hashMap.keySet()) {
            returnString += key.toString() + "; Times: " + hashMap.get(key) + System.lineSeparator();
        }
        return returnString;
    }

    public static void consoleLinkedList(List<String> lines) {
        for (String line : lines) {
            console.append(line + System.lineSeparator());
        }
    }

    public static LinkedList<String> filter(LinkedList<String> lines, Date dateFrom, Date dateTo, String fileName) {
        try {
            LinkedList<String> returnList = new LinkedList<>();
            for (String s : lines) {
                if (s.split("\"")[1].contains(fileName)) {
                    if (isFromTo(dateFrom, dateTo, getDateFromString(s))) {
                        returnList.add(s);
                    }
                }
            }
            return returnList;
        } catch (Throwable e) {
            console.append("!Some thing went wrong when filtering Exception:" + e.getMessage() + "!");
        }
        return null;
    }

    public static boolean isFromTo(Date from, Date to, Date date) {
        //System.out.println(date);
        try {
            return from.compareTo(date) * date.compareTo(to) >= 0;
        } catch (Throwable a) {
            console.setText("Some thing went wronge (on line 144): " + a.toString());
        }
        return false;
    }

    public static Date getDateFromString(String s) {
        try {
            s = s.split("\\[")[1].split("\\]")[0];

            SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");

            String dateString = format.format(new Date());
            Date date = null;
            try {
                date = format.parse(s);
                //System.out.println(date);
            } catch (ParseException ex) {
            }
            return date;
        } catch (Throwable a) {
            console.setText("Some thing went wronge (on line 164): " + a.toString());
        }
        return null;
    }

    public static void makeOutPut() {
        //System.out.println("Hello world");
        JFrame frame = new JFrame("Sorter");
        frame.add(console);
        console.setEditable(true);
        //console.setBounds(0, 0, 880, 550);
        scrol = new JScrollPane(console);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //frame.setLayout(null);
        frame.setBounds(100, 100, 900, 600);

        frame.revalidate();
        scrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //scrol.setBounds(0, 0, 880, 550);
        //frame.setResizable(false);
        frame.add(scrol);
    }

    public static LinkedList<File> getFilesFromDir(File dir) {
        LinkedList<File> arrayFolders = new LinkedList<>();
        for (File fileInFolderNotSorted : dir.listFiles()) {
            if (fileInFolderNotSorted.isDirectory()) {
                arrayFolders.addAll(Main.getFilesFromDir(fileInFolderNotSorted));
            } else {
                arrayFolders.add(fileInFolderNotSorted);
            }
        }
        return arrayFolders;
    }

    public static Date toFormatedDate(String notFormated) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String dateString = format.format(new Date());
            Date date = format.parse(notFormated);
            return date;
        } catch (ParseException ex) {
        }
        return null;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if (selectedFiles != null) {
            f=selectedFiles;
            getFilters();
        }else{
            System.exit(0);
        }
    }

}
