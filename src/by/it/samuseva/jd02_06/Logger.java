package by.it.samuseva.jd02_06;

import java.io.*;
import java.util.Date;


class Logger {
    private static volatile Logger instance;
    private static String loggerFile = getPath("log.txt");

    private static String getPath(String fileName) {
        String pathDir = System.getProperty("user.dir") + File.separator + "src" + File.separator;
        String pathFolder = Logger.class.getName().replace(Logger.class.getSimpleName(), "").replace(".", File.separator);
        return pathDir+pathFolder+fileName;
    }

    public static Logger getInstance(){
        Logger localInstance = instance;
        if (localInstance == null){
            synchronized (Logger.class) {
                localInstance = instance;
                if (localInstance==null){
                    localInstance = new Logger();
                    instance = localInstance;
                }
            }
        }
        return instance;
    }
    public void save (String information) {
        Date date = new Date();
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(loggerFile,true)));
            writer.printf("%s %s\n", date.toString(), information);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
