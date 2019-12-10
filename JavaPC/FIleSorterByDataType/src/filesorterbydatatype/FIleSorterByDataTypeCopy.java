/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesorterbydatatype;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Martin
 */
public class FIleSorterByDataTypeCopy {

    private static final JTextArea console = new JTextArea();
    private static JScrollPane scrol;
    private static Boolean boolRun = false;
    private static final FilesInFolder foldersInSortedFolder = new FilesInFolder();
    private static final ArrayList<File> filesInNotSorted = new ArrayList<>();
    private static final ArrayList<File> foldersInNotSorted = new ArrayList<>();
    private static Thread sortingThread;
    private static JButton stopButton;
    private static JButton redoButton;
    private static String newDir;
    private static File folder;
    private static File sortedFolder;

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser(""); // File choser to chose settings
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Only files excepted
        fileChooser.setDialogTitle("Settings...."); // title of filechooser window
        fileChooser.setCurrentDirectory(new File("E:")); // Location where file choser start
        int val = fileChooser.showOpenDialog(null); // open window returning int value if user chose file
        if (val == JFileChooser.APPROVE_OPTION) { // If user sucesfully chose file
            loadSettings(fileChooser.getSelectedFile()); // Send file to method loadsetinfs
        } else { // else
            System.exit(0); // shutdown we could ask him again but why?
            // To ask again we would propably use main(args);
        }
    }

    public static void loadSettings(File f) { // LoadSettings from file
        if (f.canRead()) { // try if it is readeble
            if (f.isFile()) { // if is it file(not directory)
                try (FileReader fr = new FileReader(f);) { // Creating fileReader inside of Try brackets make him
                                                           // automaticly close after every thing is done.
                    String readed = ""; // Read file into String ↑
                    int i; // |
                    while ((i = fr.read()) != -1) { // |
                        readed += (char) i; // |
                    } // |
                    // System.out.println(readed); |
                    // fr.close(); We dont have to close FileReader →---------------------↑
                    String[] lines = readed.split(System.lineSeparator());// Separete String to lines
                    for (String line : lines) { // For each line example "Compresed:zip,rar,7zip"
                        String[] folder = line.split(":"); // Separete String to 2 parts
                        // Frst is Folder name "Commpresed" ←-----------←
                        // Secound are Extentions "zip,rar,7zip" |
                        String[] data = folder[1].split(","); // Separets Extentions to single ones "zip" |
                        for (String dataType : data) { // for each Extention |
                            foldersInSortedFolder.addDataTypeIntoFolder(dataType, folder[0]); // Add Extention to folder
                                                                                              // From →------------↑
                            // end for each Extention
                        }
                    } // End of for each line
                } catch (Exception ex) {
                    System.out.print(ex.getMessage());// If file is ocrupded or something is wrong like file is emply
                }
            }
        }
        start(); // Start
    }

    public static void start() {
        JFileChooser fileChooser = new JFileChooser(""); // FileChooser to chose folder to sort
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Only folder are axcepted
        fileChooser.setDialogTitle("Which folder to sort?"); // Seting title
        fileChooser.setCurrentDirectory(new File("E:")); // Setting directory
        int val = fileChooser.showOpenDialog(null);
        if (val == JFileChooser.APPROVE_OPTION) {
            init(fileChooser.getSelectedFile()); // Starting sorting proces for chosen file
        } else {
            System.exit(0);
        }
    }

    public static void init(File f) { // Sorting proces for folder that you send into
        JFrame frame = new JFrame("Sorter");
        frame.add(console);
        frame.repaint();
        console.setEditable(false); // dont letting user to change console log
        console.setBounds(0, 0, 750, 550); // Making console so user can see what is happening
        scrol = new JScrollPane(console); // Making scroll
        // New thread becouse we will be sorting every 0.1 minutes
        sortingThread = new Thread() {
            @Override
            public void run() {
                while (true) { // Infinite loop
                    try {
                        sort(f.toString()); // Sort folder
                        console.append("-----------------------------" + "\n"); // Every loop making new line
                        Thread.sleep(3000); // Waiting for 0.1 minutes
                        scrol.getVerticalScrollBar().setValue(scrol.getVerticalScrollBar().getMaximum());
                    } catch (InterruptedException ex) { // Error catch becouse we are using Thread.sleep();
                        console.append(ex.getMessage() + "\n"); // Print error to user
                    }
                } // End of loop

            }
        };
        sortingThread.start();
        sortingThread.suspend();

        // Starting sortingThread
        // Making new JFrame with title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting windows exit operation to shut down app
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); // Seting visible
        frame.setLayout(null); // Absolute loyout
        frame.setBounds(100, 100, 900, 600); // Setting size of window
        // Making new button to stop sorting
        stopButton = new JButton("Start");
        stopButton.setBounds(750, 450, 125, 100);
        stopButton.setBackground(Color.YELLOW);
        frame.add(stopButton);
        stopButton.addActionListener((ActionEvent arg0) -> { // Making him listen to any action like click
            if (boolRun) { // if sorting is running then it will stop it and change button and color
                boolRun = false;
                sortingThread.suspend();
                stopButton.setBackground(Color.GREEN);
                stopButton.setText("RUN");
            } else { // else it will start sorting set color red and set text to STOP
                stopButton.setBackground(Color.RED);
                stopButton.setText("STOP");
                boolRun = true;
                sortingThread.resume();
            }
        });
        redoButton = new JButton("REDO");
        redoButton.setBackground(Color.yellow);
        redoButton.setBounds(750, 325, 125, 100);

        redoButton.addActionListener((ActionEvent arg0) -> {
            if (boolRun) {
                boolRun = false;
                sortingThread.suspend();
                stopButton.setBackground(Color.GREEN);
                stopButton.setText("RUN");
                redo();
                console.append("\n\n---REDO---\n\n");
            }
        });

        frame.add(redoButton);
        frame.revalidate();
        scrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrol.setBounds(0, 0, 750, 550);
        frame.add(scrol);

    }

    public static void checkIfSortedFolderIsDamaged() {
        if (sortedFolder.exists() && sortedFolder.isDirectory()) {
            File check;
            for (String s : foldersInSortedFolder) {
                // console.append(s + "\n");
                check = new File(sortedFolder.getAbsolutePath() + "\\" + s);
                if (check.isDirectory() && check.exists()) {
                    // console.append("1\n");
                } else {
                    if (!s.equalsIgnoreCase("general")) {
                        console.append(
                                "DID YOU DELETE FOLDER: " + s + ". Making new one for you: " + check.mkdir() + "\n");
                    }
                }
            }
            if (foldersInSortedFolder.getFromReverseMap("general") != null) {
                for (String s : foldersInSortedFolder.getFromReverseMap("general")) {
                    // console.append(s + "\n");
                    check = new File(sortedFolder.getAbsolutePath() + "\\" + s);
                    if (check.isDirectory() && check.exists()) {
                        // console.append("1\n");
                    } else {
                        if (!s.equalsIgnoreCase("general")) {
                            console.append("DID YOU DELETE FOLDER: " + s + ". Making new one for you: " + check.mkdir()
                                    + "\n");
                        }
                    }
                }
            }
        } else {
            console.append("DID YOU DELETE FOLDER: " + sortedFolder.getName() + ". Making new one for you: " + sortedFolder.mkdir() + "\n");
            checkIfSortedFolderIsDamaged();
        }
    }

    public static void redo() {
        HashMap<String, File> hashMapOfFiles = new HashMap<>();
        for (File f : getFilesFromDir(sortedFolder)) {
            hashMapOfFiles.put(f.getName(), f);
        }

        folder.mkdir();

        for (File folder : foldersInNotSorted) {
            folder.mkdir();
            // console.append(folder.getAbsolutePath()+" : "+folder.mkdir()+"\n");

        }

        for (File fileEntry : filesInNotSorted) {
            if (hashMapOfFiles.containsKey(fileEntry.getName())) {
                File rename = hashMapOfFiles.get(fileEntry.getName());
                rename.renameTo(fileEntry);
            }
        }
    }

    /**
     * @param dir URL of folder to sort
     */
    public static void sort(String dir) { // Sorting folder
        console.append("Sorting folder: " + dir + "\n");
        folder = new File(dir);
        Pattern dirPattern = Pattern.compile("(.*)([\\\\]{1})(.*)"); // Pattern to get folder name and location
        Matcher matcherDir = dirPattern.matcher(dir);
        matcherDir.matches();
        // Setting newDir to the same with just sorted before name
        newDir = matcherDir.group(1) + matcherDir.group(2) + "sorted" + matcherDir.group(3);
        sortedFolder = new File(newDir);
        checkIfSortedFolderIsDamaged();

        sortedFolder.mkdir(); // Making folder
        // .=anything
        // |*=any time
        // || []=chars
        // || | {n}=n times
        // || | | ()=groups
        // || | | Group 0 is all
        Pattern fileEnd = Pattern.compile("(.*)([.]{1})(.*)");// Patter to find file extention

        for (final File fileInDir : sortedFolder.listFiles()) { // Getting all folders from sortedFolder and adding them
                                                                // to our list of extentions
            if (foldersInSortedFolder.contains(fileInDir.getName()) == null && fileInDir.isDirectory()) {
                if (foldersInSortedFolder.secoundContains(fileInDir.getName())) {

                } else {
                    foldersInSortedFolder.addDataTypeIntoFolder(fileInDir.getName(), "general");
                }
            }
        }

        console.append("In sorted folder we already have: \n" + foldersInSortedFolder.toString() + "\n");

        // Getting all files from all folder and subfolders of dir.using method
        // getFilesFromDir(File f);
        for (File fromSortedDir : getFilesFromDir(folder)) {
            if (filesInNotSorted.contains(fromSortedDir)) {
            } else {
                filesInNotSorted.add(fromSortedDir);
            }
        }

        for (File fromSortedDir : getFoldersFromDir(folder)) {
            if (foldersInNotSorted.contains(fromSortedDir)) {
            } else {
                foldersInNotSorted.add(fromSortedDir);
            }
        }

        console.append("Not sorted files we have:\n");// Using our own method to write all unsorted files to user.
        getFilesFromDir(folder).forEach((fileEntry) -> {
            // For each file in dir
            Matcher fileEndMatcher = fileEnd.matcher(fileEntry.getName());// using matcher with patter to find extention
            if (fileEndMatcher.matches()) { // if file have extention and start not supporting ".class"
                // console.append(fileEndMatcher.group(3)+"\n");//3th group is extention
                String extention = fileEndMatcher.group(3); // Saving extention of file
                // System.out.println(fileEndMatcher.group(3));
                if (!extention.equalsIgnoreCase("tmp")) { // Igonring tmp data
                    String saveTo = foldersInSortedFolder.contains(extention); // Getting if this type of file is
                                                                               // allready in SortedFolder
                    // console.append("Extetion "+extention+"\n");
                    if (saveTo != null) {// If any file with same Extention is allready in sortedFolder
                        // console.append("save to is NOT null\n");
                        if (saveTo.equalsIgnoreCase("general")) {// If is in general then move file into that folder
                            // console.append(newDir + "\\" + extention + "\\" +
                            // fileEndMatcher.group(0)+"\n");
                            File k = new File(newDir + "\\" + extention + "\\" + fileEndMatcher.group(0));
                            if (k.canRead()) {
                                console.append("DUPLI FILE: " + fileEntry.getName() + "\n");
                            } else {
                                console.append(
                                        "File:  ►" + fileEntry.getName() + "◄ moved?:" + fileEntry.renameTo(k) + "\n");
                                // moving file
                            }
                            // rename move file
                        } else {// if it is not in general
                            // console.append(newDir + "\\" + saveTo + "\\" + fileEndMatcher.group(0)+"\n");
                            final File newFolder = new File(newDir + "\\" + saveTo);
                            newFolder.mkdir(); // making new folder with name ass folder form our list
                            File k = new File(newDir + "\\" + saveTo + "\\" + fileEndMatcher.group(0));
                            if (k.canRead()) {
                                console.append("DUPLI FILE: " + fileEntry.getName() + "\n");
                            } else {
                                console.append(
                                        "File:  ►" + fileEntry.getName() + "◄ moved?:" + fileEntry.renameTo(k) + "\n");
                                fileEntry.renameTo(k);// moving file
                            }
                        }
                    } else {
                        // console.append("save to is null\n");
                        // if folder for this extention does not exsist yet
                        final File newFolder = new File(newDir + "\\" + extention);
                        newFolder.mkdir();// Make folder
                        foldersInSortedFolder.addDataTypeIntoFolder(extention, "general");// Adding folder so it would
                                                                                          // not try to make it again
                        fileEntry.renameTo(new File(newDir + "\\" + extention + "\\" + fileEndMatcher.group(0)));// moving
                                                                                                                 // file
                    }
                }
            } else {
                if (!fileEntry.isDirectory()) {
                    String saveTo = foldersInSortedFolder.contains(fileEntry.getName());
                    if (saveTo != null) {// If any file with same Extention is allready in sortedFolder
                        // console.append("save to is NOT null\n");
                        if (saveTo.equalsIgnoreCase("general")) {// If is in general then move file into that folder
                            // console.append(newDir + "\\" + extention + "\\" +
                            // fileEndMatcher.group(0)+"\n");
                            File k = new File(newDir + "\\" + fileEntry.getName() + "\\" + fileEntry.getName());
                            if (k.canRead()) {
                            } else {
                                console.append(
                                        "File:  ►" + fileEntry.getName() + "◄ moved?:" + fileEntry.renameTo(k) + "\n");
                                fileEntry.renameTo(k);// moving file
                            }
                            // rename move file
                        } else {// if it is not in general
                            // console.append(newDir + "\\" + saveTo + "\\" + fileEndMatcher.group(0)+"\n");

                            final File newFolder = new File(newDir + "\\" + saveTo);
                            newFolder.mkdir();

                            File k = new File(newDir + "\\" + saveTo + "\\" + fileEntry.getName());
                            if (k.canRead()) {
                                console.append("DUPLI FILE: " + fileEntry.getName() + "\n");
                            } else {
                                console.append(
                                        "File:  ►" + fileEntry.getName() + "◄ moved?:" + fileEntry.renameTo(k) + "\n");
                                fileEntry.renameTo(k);// moving file
                            }
                        }
                    } else {
                        // console.append("save to is null\n");
                        // if folder for this extention does not exsist yet
                        final File newFolder = new File(newDir + "\\" + fileEntry.getName());
                        newFolder.mkdir();// Make folder
                        foldersInSortedFolder.addDataTypeIntoFolder(fileEntry.getName(), "general");// Adding folder so
                                                                                                    // it would not try
                                                                                                    // to make it again
                        fileEntry.renameTo(new File(newDir + "\\" + fileEntry.getName() + "\\" + fileEntry.getName()));// moving
                                                                                                                       // file
                    }
                }
            }
        });
    }

    public static ArrayList<File> getFoldersFromDir(File dir) {
        ArrayList<File> arrayFolders = new ArrayList<>();// Returning list of files in every subfodler
        for (File fileInFolderNotSorted : dir.listFiles()) {// for each file and folder in dir
            if (fileInFolderNotSorted.isDirectory()) {// if is it folder
                arrayFolders.add(fileInFolderNotSorted);
                arrayFolders.addAll(getFoldersFromDir(fileInFolderNotSorted));// then do recursion and add it into our
                                                                              // ArrayList of files
            } else {

            }
        }
        return arrayFolders;// on end return
    }

    public static ArrayList<File> getFilesFromDir(File dir) {
        ArrayList<File> arrayFolders = new ArrayList<>();// Returning list of files in every subfodler
        for (File fileInFolderNotSorted : dir.listFiles()) {// for each file and folder in dir
            if (fileInFolderNotSorted.isDirectory()) {// if is it folder
                arrayFolders.addAll(getFilesFromDir(fileInFolderNotSorted));// then do recursion and add it into our
                                                                            // ArrayList of files
            } else {
                arrayFolders.add(fileInFolderNotSorted);// if it is a file add it
            }
        }
        return arrayFolders;// on end return
    }

    public static String arrayToString(Collection a) {
        String returningString = "";
        for (Object b : a) {
            returningString += "►" + b.toString() + "◄\n";
        }
        return returningString;
    }

}
