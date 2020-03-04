package by.it.tarasevich.jd02_06;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    private static volatile Logger instance;

    private Logger(){

    }

    public static Logger getInstance() {
        Logger logger = instance;
        if (logger == null) {
            synchronized (Logger.class) {
                logger = instance;
                if (logger == null) {
                    instance = logger = new Logger();
                }
            }
        }
        return logger;
    }
}
