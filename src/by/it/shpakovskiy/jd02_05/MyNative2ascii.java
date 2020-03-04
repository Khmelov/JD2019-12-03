package by.it.shpakovskiy.jd02_05;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

class MyNative2ascii {
    public static void main(String[] args) {
        Map<String, String> map = getMapFromFile(
                System.getProperty("user.dir") + "/src/by/it/shpakovskiy/jd02_05/myText.txt");
        map.forEach((k, v) -> System.out.println(k + " = " + v));
        System.out.println("_____________________________________");
        convertValuesToHex(map);
        printMapToFile(map, System.getProperty("user.dir") + "/src/by/it/shpakovskiy/jd02_05/myText.properties");
        map.forEach((k, v) -> System.out.println(k + " = " + v));
    }

    static Map<String, String> getMapFromFile(String fileName) {
        Map<String, String> map = new LinkedHashMap<>();
        try {
            Files.lines(Paths.get(fileName)).map(o -> o.split("=")).forEach(o -> map.put(o[0], o[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    static void convertValuesToHex(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            StringBuilder stringBuilder = new StringBuilder();
            entry.getValue().chars().mapToObj(Integer::toHexString).
                    forEach(o -> stringBuilder.append("\\u0").append(o));
            entry.setValue(stringBuilder.toString());
        }
    }

    static void printMapToFile(Map<String, String> map, String filePath) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath))) {
            String newline = "";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                printWriter.print(newline);
                printWriter.print(entry.getKey() + "=" + entry.getValue());
                newline = "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
