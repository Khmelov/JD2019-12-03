package by.it.borodachev.Calc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class LogClass {
    private static volatile LogClass instance;
    private String logFileName;
    private static Report repFormat;

    public static void setRepFormat(Report rep) {
        repFormat = rep;
    }

    public void save2LogFile(String msg) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFileName, true))) {
            writer.println(msg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void save2log(String msg) {
        String repStr=repFormat.getDate()+" " +msg;
        System.out.println(repStr);
        save2LogFile(repStr);
    }

    public void save2log(String msg,Exception e) {
        String repStr=repFormat.getDate()+" "+msg+" "+repFormat.getError(e);
        System.out.println(repStr);
        save2LogFile(repStr);
    }
    private LogClass() {
        logFileName = Helper.getPath("log.txt", LogClass.class);
    }

    //метод доступа к экземпляру
    public static LogClass getInstance() {
        LogClass localInstance = instance; //попытка получить объект
        if (localInstance == null) { //первая проверка (для скорости)
            synchronized (LogClass.class) {
                localInstance = instance; //вторая проверка для надежности
                if (localInstance == null) {
                    instance = localInstance = new LogClass(); //создание
                }
            }
        }
        return localInstance; //возврат
    }
}
