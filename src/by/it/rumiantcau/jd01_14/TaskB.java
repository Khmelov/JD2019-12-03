package by.it.rumiantcau.jd01_14;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskB {
    public static void main(String[] args) {
        //считываем текст из файла построчно в стрингбилдер
        StringBuilder str = readTextFile(Helper.getPath("text.txt", TaskA.class));
        //подсчитываем количество знаков пунктуации
        int punctuationCount = patternMatcher(str , "[.?!,;:()]");
        //подсчитываем количество слов
        int wordsCount = patternMatcher(str, "[А-Яа-яёЁA-Za-z]{1,}");
        String result = "words=" + wordsCount + ", punctuation marks=" + punctuationCount;
        System.out.println(result);
        //записываем результат в файл
        writeTextFile(Helper.getPath("resultTaskB.txt", TaskA.class), result);
    }

    private static int patternMatcher(StringBuilder str, String s) {
        int wordsCount = 0;
        Matcher words = Pattern.compile(s).matcher(str);
        while (words.find()) {
            wordsCount++;
        }
        return wordsCount;
    }

    private static StringBuilder readTextFile(String fileNameB) {
        StringBuilder str = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileNameB), StandardCharsets.UTF_8))){
            while ((line = reader.readLine()) != null) {
                str.append(line).append(" ");
            }
        } catch (IOException e) {
            System.out.println("It is a trap!");
        }
        return str;
    }

    private static void writeTextFile(String fileNameB, String result) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(fileNameB), StandardCharsets.UTF_8))){
            writer.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
