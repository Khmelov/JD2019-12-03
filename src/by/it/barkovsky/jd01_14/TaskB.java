package by.it.barkovsky.jd01_14;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskB {

    private static String getFileName(String name) {
        String src = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        String strPackage = TaskB.class.getPackage().getName();
        String relPath = strPackage.replace(".", File.separator);
        return src + relPath + File.separator + name;
    }
    public static void main(String[] args)  {

        String fileName = getFileName("text.txt");
        String line = countWordsAndSymbol(fileName);
        String resultFileName = getFileName("resultTaskB.txt");
        saveLineToTheFileTxt(line, resultFileName);

    }

    private static String countWordsAndSymbol(String fileName) {
        String line = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            Pattern words = Pattern.compile("[а-яА-ЯёЁ]+");
            Pattern symbol = Pattern.compile("[^а-яА-ЯёЁ ]+");
            int countWords = 0;
            int countSymbol = 0;
            while(reader.ready()){
                String s = reader.readLine();
                Matcher matcher1 = words.matcher(s);
                Matcher matcher2 = symbol.matcher(s);
                while (matcher1.find())countWords++;
                while(matcher2.find()) countSymbol++;
            }
            line = "words="+countWords+", punctuation marks="+countSymbol+".";
            System.out.print(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    private static void saveLineToTheFileTxt(String line, String resultFileName) {
        try (PrintWriter printWriter = new PrintWriter((new FileWriter(resultFileName)))){
            printWriter.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
