package by.it.barkovsky.jd01_15;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TaskB {
    private static StringBuilder sb = new StringBuilder();
    private static String getFileName (String fileName){
        String src = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        String pack = TaskB.class.getPackage().getName();
        String path = pack.replace(".", File.separator);
        return src + path + File.separator + fileName;
    }

    public static void main(String[] args) {
        String fileN = getFileName("TaskB.java");
        readF(fileN);
        writeF();
        showF();
    }

    private static void readF(String fileN) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileN))) {
            String str;
            while ((str = reader.readLine()) !=null) {
                StringBuilder sbline = new StringBuilder(str).append('\n');
                if (str.contains("/") && check(str)) {
                    sbline.delete(sbline.indexOf("/"), sbline.length()).append('\n');
                }
                String change = sbline.toString();
                if (change.contains("*") && check(str)) {
                    continue;
                }
                sb.append(sbline);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean check(String str) {
        return !str.contains("contains") && !str.contains("indexOf");
    }

    private static void writeF() {
        String writingFileName = getFileName("TaskB.txt");
        try (BufferedWriter writer = new BufferedWriter(new PrintWriter(writingFileName))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void showF() {
        try (BufferedReader br = new BufferedReader(new FileReader(getFileName("TaskB.txt")))) {
            String s;
            while ((s = br.readLine()) !=null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
