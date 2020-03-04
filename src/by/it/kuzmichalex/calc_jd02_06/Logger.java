package by.it.kuzmichalex.calc_jd02_06;


import java.io.*;
import java.util.ArrayDeque;

class Logger {
    static private volatile Logger singleInstance;
    static private ArrayDeque<String> logList = new ArrayDeque<>();
    static private String sLogFileName = FHelper.getFileName("log.txt", Logger.class);
    static private ReportBuilder reportBuilder;

    /**
     * Конструктор private. Чтобы никто не вызывал. Только мы сами.
     */
    private Logger() {
        logList = new ArrayDeque<>();
        sLogFileName = FHelper.getFileName("log.txt", Logger.class);
        reportBuilder = new ReportBuilderFull();
    }

    /**
     * Получить синглетный экземпляр
     */
    public static Logger getInstance() {
        if (singleInstance != null) return singleInstance;      //Уже есть, больше не будем. Потому что сигнлтон.
        synchronized (Logger.class) {
            if (singleInstance != null)
                return singleInstance;          //Пока чухались, появился. Больше не будем. Потому что синглтон.
            singleInstance = new Logger();      //А вот теперь точно нет. Создадим и вернём.
            return singleInstance;
        }
    }

    /**
     * Установим конструктор репорта, который будет применяться для посторения отчёта
     */
    void setReportBuilder(ReportBuilder rp) {
        reportBuilder = rp;
    }

    /**
     * load log into ArrayDeque
     */
    void loadLog() {
        try (BufferedReader br = new BufferedReader(new FileReader(sLogFileName))) {
            logList.clear();
            String logString;
            while (true) {
                logString = br.readLine();
                if (logString == null) break;
                logList.addLast(logString);
                if (logList.size() > 50){ logList.removeFirst(); }
            }
        } catch (IOException e) {
            System.out.println(LanguageManager.get(Messages.error_read_log) + e.getMessage());
        }
    }

    /**
     * Показываем результат в консоли и тут же пишем в файл.
     *
     * @param sMessage - Сообщение, которое надо записать.
     */
    void printAndLog(String sMessage, CalcException calcException) {
        String messageToLog=reportBuilder.buildBodyString(sMessage, calcException);
        System.out.println(messageToLog);
        logList.addLast(messageToLog);
        if (logList.size() > 50){ logList.removeFirst(); }

        try (PrintWriter pw = new PrintWriter(new FileWriter(sLogFileName))
        ) {
            for (String s : logList) {
                pw.print(s);
                if (!s.endsWith("\n")) pw.println();
            }
        } catch (IOException e) {
            System.out.println(LanguageManager.get(Messages.error_write_log) + e.getMessage());
        }
    }
}
