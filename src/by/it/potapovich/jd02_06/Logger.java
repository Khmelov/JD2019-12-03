package by.it.potapovich.jd02_06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;

public class Logger {
    private static volatile Logger instance;

    public static Logger getInstance() {
        Logger logger = instance;
        if (logger == null) {
            synchronized (Logger.class) {
                logger = instance;
                if (logger == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String msg) {
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        PrintWriter logWriter = null;
        try {
            logWriter = new PrintWriter(new FileWriter(getPath("log.txt", Logger.class), true));

            logWriter.write(currentTime + ": " + msg + "\n");
            logWriter.flush();
            logWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    private static String getPath(String fileName, Class<?> aClass) {
        String root = System.getProperty("user.dir");
        String strPackage = aClass.getName().replace(aClass.getSimpleName(), "");
        String path = root + File.separator + "src" + File.separator + strPackage.replace(".", File.separator);
        fileName = path + fileName;
        return fileName;
    }


}
