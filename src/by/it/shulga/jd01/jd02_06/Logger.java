package by.it.shulga.jd01.jd02_06;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;

public class Logger {
    private static volatile Logger instance;
    private static final String loggerFile = Helper.pathToFile("log.txt", Logger.class);
    private static ArrayDeque<String> logList = new ArrayDeque<>();
    private String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

    public Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    void logWrite(String message) {
        loadLog();
        logList.add(currentTime + " | " + message);
        try (PrintWriter pw = new PrintWriter(new FileWriter(loggerFile))) {
            for (String s : logList) {
                pw.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void loadLog() {
        try (BufferedReader br = new BufferedReader(new FileReader(loggerFile))) {
            logList.clear();
            String logLine;
            while (true) {
                logLine = br.readLine();
                if (logLine == null) break;
                logList.addLast(logLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}