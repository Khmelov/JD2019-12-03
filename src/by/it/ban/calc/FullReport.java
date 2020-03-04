package by.it.ban.calc;

import java.io.*;
import java.text.DateFormat;
import java.util.Date;

public class FullReport extends Report {
    private File file;
    private static int dateStyle=DateFormat.FULL;
    private static int timeStyle=DateFormat.FULL;

    public FullReport() {
        file = new File(System.getProperty("user.dir")
                + File.separator
                + "src"
                + File.separator
                + Report.class.getName().replace(Report.class.getSimpleName(), "").replace(".", File.separator)
                +"FullReport.txt");
    }

    @Override
    void printTitle() {
        try (FileWriter out = new FileWriter(file, false))
        {
            out.append("    ПОЛНЫЙ ОТЧЕТ     ").append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void printStart() {
        Date d = new Date();
        try (FileWriter out = new FileWriter(file, true))
        {
            out     .append("Время начала работы программы: ")
                    .append(DateFormat
                    .getDateTimeInstance(dateStyle, timeStyle).format(d))
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void printEnd() {
        Date d = new Date();
        try (FileWriter out = new FileWriter(file, true))
        {
            out     .append("Время окончания работы программы: ")
                    .append(DateFormat
                            .getDateTimeInstance(dateStyle, timeStyle).format(d))
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void printOperation(String string) {
        Date d = new Date();
        try (FileWriter out = new FileWriter(file, true))
        {
            out.append(DateFormat
                    .getDateTimeInstance(dateStyle, timeStyle).format(d))
                    .append("   введено: ").append(string).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void printResult(String string) {
        Date d = new Date();
        try (FileWriter out = new FileWriter(file, true))
        {
            out.append(DateFormat
                    .getDateTimeInstance(dateStyle, timeStyle).format(d))
                    .append(" результат: ").append(string).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void printError(Exception exp) {
        Date d = new Date();
        try (FileWriter out = new FileWriter(file, true))
        {
            out.append(DateFormat
                    .getDateTimeInstance(dateStyle, timeStyle).format(d))
                    .append("  ошибка:");
            for (StackTraceElement element : exp.getStackTrace()) {
                out.append(element.toString()).append("\n");
            }
            out.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
