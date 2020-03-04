package by.it.tarasevich.jd02_06;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
    public static void main(String[] args) {

        String p = System.getProperty("user.dir") + "/src/by/it/tarasevich/";
        String file1 = p + "jd02_06/log.txt";

        try (PrintWriter pr = new PrintWriter(new FileWriter(p))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}