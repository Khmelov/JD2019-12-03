package by.it.akhmelev.jd02_06;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Logger {

    private static volatile Logger logger;

    private String logFile;

    private Logger() {
        logFile = System.getProperty("user.dir") +
                "/src/by/it/akhmelev/jd02_06/log.txt";
    }

    static Logger getInstance() {
        Logger localLogger = logger;
        if (localLogger == null) {
            synchronized (Logger.class) {
                localLogger = logger;
                if (localLogger == null) {
                    localLogger = new Logger();
                    logger = localLogger;
                }
            }
        }
        return localLogger;
    }

    void save(String message) {
        try (
                PrintWriter writer = new PrintWriter(
                        new FileWriter(logFile,true)
                )
        ) {
            writer.println(message);
        } catch (IOException e) {
            throw new RuntimeException("error I/O", e);
        }
    }

}
