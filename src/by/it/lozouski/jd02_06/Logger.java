package by.it.lozouski.jd02_06;

import by.it.lozouski.jd01_14.Helper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Logger {
    private static volatile Logger instance;
    private static String logFileTxtPath = Helper.getPath("logFileTaskA.txt", Logger.class);

    public static Logger getInstance() {
        Logger localInstance = instance;
        if (localInstance == null) {
            synchronized (Logger.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = new Logger();
                    instance = localInstance;
                }
            }
        }
        return localInstance;
    }

    public void addLogMessage(String logMessage) {
        try (final PrintWriter logWriter = new PrintWriter(new FileWriter(logFileTxtPath, true))) {
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            logWriter.println(currentTime + " " + logMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
