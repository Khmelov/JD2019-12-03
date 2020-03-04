package by.it.popkov.calc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

class SingletonLog {
    private SingletonLog(){}
    static private volatile SingletonLog instance;
    private static String logFileName = CalcFile.fileFullName(SingletonLog.class, "log.txt");

    public static String getLogFileName() {
        return logFileName;
    }

    public static SingletonLog getInstance() {
        SingletonLog localInstance = instance;
        if (localInstance == null){
            synchronized (SingletonLog.class){
                localInstance = new SingletonLog();
                instance = localInstance;
            }
        }
        return localInstance;
    }

    public void reWriteLog(String newMessage) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(logFileName));
            lines.remove(0);
            lines.add(newMessage);
            Files.write(Paths.get(logFileName), lines);
        } catch (IOException e) {
            System.out.println("Error reWriteLog");
        }
    }

    public void writeLog(String message) {
        String now = LocalDateTime.now() + ": ";
        try {
            if (Files.exists(Paths.get(logFileName)) && Files.readAllLines(Paths.get(logFileName)).size() >= 50)
                reWriteLog(now + message);
            else {
                try (final PrintWriter logName = new PrintWriter
                        (new FileWriter(logFileName, true))) {
                    logName.println(now + message);
                } catch (IOException e) {
                    System.out.println("Error writeLog");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
