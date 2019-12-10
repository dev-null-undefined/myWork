
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Martin
 */
public class FileCounter {

    /**
     * @param args the command line arguments
     */
    private static final String Usage = "\n"//1     3    5    7    9   11   13   15   17    19     20
            + "Usage: File-counter sourceFolder [-p] [-o] [-t] [-s] [-i] [-d] [-f] [-n] [-ext] [-help] \n"
            + "Options:\n"
            + "     -p (true/false) preview only. Default false.\n"
            + "     -o (Integer) Options: 1 last modify\n"
            + "                           2 last modify desc\n"
            + "                           3 alfabet\n"
            + "                           4 alfabet desc\n"
            + "     -help display this menu.\n"
            + "Counter settings:\n"
            + "     -t (Integer) Count of chars to delete before each file name.\n"
            + "     -s (Integer) count of char before deleting part.\n"
            + "     -i (Integer) where to put counter.\n"
            + "     -d (Integer) how many digits for better formation.\n"
            + "Filtering:\n"
            + "     -f (true/false) take files from sub folders too. Default values false.\n"
            + "     -n (String) have to have some String in FileName.\n"
            + "     -ext (String) only files with same extetion will be changed.\n";
    static String paramSourceForder;
    static Integer paramOrder = null;
    static int countCharsBeforeDelete = 0;
    static int countCharsToDelete = 0;
    static int counterPos = 0;
    static int digits = 0;
    static boolean paramSubFolders = false;
    static boolean preview = false;
    static String paramExtention;
    static String paramFilePart = null;

    public static void main(String[] args) {
        try {
            // TODO code application logic here
            if (args.length == 0 || args.length >= 20) {
                System.out.println(Usage);
            }
            paramSourceForder = args[0];
            int option = 0;
            String param;
            for (int index = 1; index < args.length; index++) {
                param = args[index];
                switch (param) {
                    case "-t":option = 1;break;
                    case "-s":option = 2;break;
                    case "-i":option = 3;break;
                    case "-d":option = 4;break;
                    case "-f":option = 5;break;
                    case "-p":option = 6;break;
                    case "-ext":option = 7;break;
                    case "-o":option = 8;break;
                    case "-help":System.out.println(Usage);break;
                    case "-n":option = 9;break;
                    default:
                        if (option == 0) {
                            System.out.println("Wrong param input try File-rename.jar -help");
                            System.exit(0);
                        } else {
                            switch (option) {
                                case 1:countCharsToDelete = Integer.valueOf(param);break;
                                case 2:countCharsBeforeDelete = Integer.valueOf(param);break;
                                case 3:counterPos = Integer.valueOf(param);break;
                                case 4:digits = Integer.valueOf(param);break;
                                case 5:paramSubFolders = Boolean.valueOf(param);break;
                                case 6:preview = Boolean.valueOf(param);break;
                                case 7:paramExtention = param;break;
                                case 8:paramOrder = Integer.valueOf(param);break;
                                case 9:paramFilePart = param; 
                            }                     
                            option = 0;
                        }
                }
            }

            /*System.out.println("paramSourceForder"+paramSourceForder+
             "\ncountCharsBeforeDelete"+countCharsBeforeDelete+
             "\ncountCharsToDelete"+countCharsToDelete+
             "\ncounterPos"+counterPos+
             "\ndigits"+digits);*/
            rename(getFilesFromDir(new File(paramSourceForder)));
        } catch (Exception e) {
            //System.out.println("Some thing went wrong:   "+e.getMessage());
        }
    }

    public static ArrayList<File> order(ArrayList<File> files) {
        ArrayList<File> orderedFiles = new ArrayList<File>();
        HashMap<Long, File> mapOfFiles;
        Set<Long> keySet;
        List<Long> keyList;
        List<String> stringKeyList;
        Set<String> stringKeySet;
        HashMap<String, File> mapOfFilesWithStringKey;
        switch (paramOrder) {
            case 1:
                mapOfFiles = new HashMap<>();
                for (File currentFile : files) {
                    mapOfFiles.put(currentFile.lastModified(), currentFile);
                }
                keySet = mapOfFiles.keySet();
                keyList = new ArrayList<Long>(keySet);
                Collections.sort(keyList);
                for (Long currentKey : keyList) {
                    File currentFile = mapOfFiles.get(currentKey);
                    orderedFiles.add(currentFile);
                }
                break;
            case 2:
                mapOfFiles = new HashMap<>();
                for (File currentFile : files) {
                    mapOfFiles.put(currentFile.lastModified(), currentFile);
                }
                keySet = mapOfFiles.keySet();
                keyList = new ArrayList<Long>(keySet);
                Collections.sort(keyList);
                for (int position = keyList.size() - 1; position >= 0; position--) {
                    File currentFile = mapOfFiles.get(keyList.get(position));
                    orderedFiles.add(currentFile);
                }
                break;
            case 3:
                mapOfFilesWithStringKey = new HashMap<>();
                for (File currentFile : files) {
                    mapOfFilesWithStringKey.put(currentFile.getName(), currentFile);
                }
                stringKeySet = mapOfFilesWithStringKey.keySet();
                stringKeyList = new ArrayList<String>(stringKeySet);
                Collections.sort(stringKeyList);
                for (String current : stringKeyList) {
                    File currentFile = mapOfFilesWithStringKey.get(current);
                    orderedFiles.add(currentFile);
                }
                break;
            case 4:
                mapOfFilesWithStringKey = new HashMap<>();
                for (File currentFile : files) {
                    mapOfFilesWithStringKey.put(currentFile.getName(), currentFile);
                }
                stringKeySet = mapOfFilesWithStringKey.keySet();
                stringKeyList = new ArrayList<String>(stringKeySet);
                Collections.sort(stringKeyList);
                for (int position = stringKeyList.size() - 1; position >= 0; position--) {
                    File currentFile = mapOfFilesWithStringKey.get(stringKeyList.get(position));
                    orderedFiles.add(currentFile);
                }
                break;
        }
        return orderedFiles;
    }

    public static void rename(ArrayList<File> files) {
        int counter = 0;
        String counterString;
        if (paramOrder != null) {
            files = order(files);
        }
        for (File currentFile : files) {
            if (paramExtention != null) {
                Pattern fileEnd = Pattern.compile("(.*)([.]{1})(.*)");
                Matcher fileEndMatcher = fileEnd.matcher(currentFile.getName());
                if (fileEndMatcher.matches()) {
                    String extention = fileEndMatcher.group(3);
                    if (paramExtention.equalsIgnoreCase(extention) == false) {
                        //System.out.println("paramExtention.equalsIgnoreCase(extention)"+paramExtention.equalsIgnoreCase(extention));
                        continue;
                    }
                }
            }
            if(paramFilePart!=null){
                if(currentFile.getName().contains(paramFilePart)==false){
                    continue;
                }
            }
            String fileName = currentFile.getName();
            fileName = fileName.substring(0, countCharsBeforeDelete) + fileName.substring(countCharsBeforeDelete + countCharsToDelete);
            if (digits == 0) {
                counterString = counter + "";
            } else {
                counterString = String.format("%0" + digits + "d", counter);
            }
            fileName = fileName.substring(0, counterPos) + counterString + fileName.substring(counterPos);
            if (preview) {
                System.out.println(fileName);
            } else {
                currentFile.renameTo(new File(currentFile.getParent() + "\\" + fileName));
            }
            counter++;

        }
    }

    public static ArrayList<File> getFilesFromDir(File dir) {
        ArrayList<File> arrayFolders = new ArrayList<>();// Returning list of files in every subfodler
        for (File fileInFolderNotSorted : dir.listFiles()) {// for each file and folder in dir
            if (fileInFolderNotSorted.isDirectory()) {// if is it folder
                if (paramSubFolders == true) {
                    arrayFolders.addAll(getFilesFromDir(fileInFolderNotSorted));
                }// then do recursion and add it into our
                // ArrayList of files
            } else {
                arrayFolders.add(fileInFolderNotSorted);// if it is a file add it
            }
        }
        return arrayFolders;// on end return
    }
}
