package by.it.shpakovskiy.jd02_06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

class Logger {
    private static volatile Logger instance;
    private File file = new File(System.getProperty("user.dir"), "/src/by/it/shpakovskiy/jd02_06/log.txt");

    private Logger() {
    }

    static Logger getInstance() {
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

    public File getFile() {
        return file;
    }

    synchronized void print(String message) {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            String date = dateFormat.format(new Date());
            writer.println(date + " - " + message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
