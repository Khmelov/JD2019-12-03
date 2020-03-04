package by.it.barkovsky.jd02_06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

public class Logger {
    private static volatile Logger instance;
    private String filename = getPath(Logger.class) + "log.txt";

    private Logger() {
    }

    static Logger createLogger() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    void log(String text) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename, true))) {
            Date d = new Date();
            out.append(DateFormat
                    .getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(d))
                    .append(" | ").append(text).append("\n");
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    private static String getPath(Class<?> aClass) {
        return System.getProperty("user.dir")
                + File.separator + "src" + File.separator +
                aClass
                        .getName()
                        .replace(aClass.getSimpleName(), "")
                        .replace(".", File.separator);
    }
}