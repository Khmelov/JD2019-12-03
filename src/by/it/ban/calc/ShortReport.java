package by.it.ban.calc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class ShortReport extends Report {
    private File file;
    private static int dateStyle = DateFormat.SHORT;
    private static int timeStyle = DateFormat.SHORT;

    public ShortReport() {
        file = new File(System.getProperty("user.dir")
                + File.separator
                + "src"
                + File.separator
                + Report.class.getName().replace(Report.class.getSimpleName(), "").replace(".", File.separator)
                + "ShortReport.txt");
    }

    @Override
    void printTitle() {
        try (FileWriter out = new FileWriter(file, false)) {
            out.append("    КРАТКИЙ ОТЧЕТ     ").append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void printStart() {
        Date d = new Date();
        try (FileWriter out = new FileWriter(file, true)) {
            out.append("Время начала работы программы: ")
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
        try (FileWriter out = new FileWriter(file, true)) {
            out.append("Время окончания работы программы: ")
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
        try (FileWriter out = new FileWriter(file, true)) {
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
        try (FileWriter out = new FileWriter(file, true)) {
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
        try (FileWriter out = new FileWriter(file, true)) {
            out.append(DateFormat
                    .getDateTimeInstance(dateStyle, timeStyle).format(d))
                    .append("  ошибка:")
                    .append(exp.getMessage())
                    .append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
