package by.it.barkovsky.jd01_14;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskC {
    private static StringBuilder sb =  new StringBuilder();

    private static String getFileName() {
        String source = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        String strPackage = TaskC.class.getPackage().getName();
        String relPath = strPackage.replace(".", File.separator);
        return source + relPath + File.separator + "resultTaskC.txt";
    }
    public static void main(String[] args) {
        String[] listOfPackages = getNameOfPackages();
        ArrayList<String> arrayListOfFiles = new ArrayList<>();
        for (String s : listOfPackages) {
            System.out.println("dir:"+s);
            String[] nameOfFiles = getNameOfFiles(s);
            for (String name : nameOfFiles) {
                System.out.println("file:"+name);
                arrayListOfFiles.add(name);
            }
        }

        saveToFileTxt(listOfPackages,arrayListOfFiles);
    }

    private static String[] getNameOfPackages() {
        String src = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        String strPackage = TaskC.class.getPackage().getName();
        String relPath = strPackage.replace(".", File.separator);
        String s = src + relPath;
        String[] split = s.split(File.separator+File.separator);
        for (int i = 0; i <split.length-1 ; i++) {
            sb.append(split[i]);
            sb.append(File.separator);
        }
        System.out.println(sb.toString());
        File file = new File(sb.toString());
        return file.list();
    }

    private static String[] getNameOfFiles(String nameOfPackage) {
        File file = new File(sb+nameOfPackage);
        return file.list();
    }

    private static void saveToFileTxt(String[] list, ArrayList<String> arrayList) {
        String fileName = getFileName();
        try( PrintWriter printWriter = new PrintWriter(new FileWriter(fileName))) {
            for (String s : list) {
                printWriter.write("dir:"+s);
                printWriter.write("\n");
            }
            for (String s : arrayList) {
                printWriter.write("file:"+s);
                printWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
