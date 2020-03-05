package by.it.cherkas.jd02_06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class Logger {

    private static volatile Logger logger;
    private static File file;
    private Logger() {
        file = new File(getPath("log.txt"));
    }

    private static String getPath(String fileName) {
        String root = System.getProperty("user.dir");
        String strPackage = Logger.class.getName().replace(Logger.class.getSimpleName(), "");
        String path = root + File.separator + "src" + File.separator + strPackage.replace(".", File.separator);
        fileName = path + fileName;
        return fileName;
    }
    public static Logger getLogger() {
        Logger localLogger = logger;
        if (localLogger == null) {
            synchronized (Logger.class) {
                localLogger = logger;
                if (localLogger == null) {
                    logger = localLogger = new Logger();
                }
            }
        }
        return logger;
    }
    public void log(String message) {
        Date d = new Date();
        try (FileWriter out = new FileWriter(file, true))
        {
            out.append(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(d))
                    .append(" | ").append(message).append("\n");

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
