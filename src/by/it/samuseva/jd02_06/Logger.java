package by.it.samuseva.jd02_06;

import java.io.*;
import java.util.Date;

class Logger {
    private static volatile Logger instance;
    private static String loggerFile = getPath("log.txt", Logger.class);

    private static String getPath(String fileName, Class<Logger> aClass) {
        String getPath = System.getProperty("user.dir") + File.separator +"scr" + File.separator;
        String getFile = aClass.getName().replace(aClass.getSimpleName(), "").replace(".", File.separator);
    return getPath+getFile+fileName;
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
    static void save (String information, Date date) throws CalcException {
        try (PrintWriter writer= new PrintWriter(loggerFile) {
        }){
            writer.printf("%s\n %t\n", information, date);
        } catch (FileNotFoundException e) {
            throw new CalcException(loggerFile+" error", e);
        }
    }
}
