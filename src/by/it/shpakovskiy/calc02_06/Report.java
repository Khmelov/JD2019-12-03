package by.it.shpakovskiy.calc02_06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

class Report {
    private String header;
    private Date beginWorkTime;
    private Date endWorkTime;
    private Locale locale;
    private int dateStile;
    private LinkedList<String> text;

    public Report(String header, Date beginWorkTime, Date endWorkTime, Locale locale, int dateStile, LinkedList<String> text) {
        this.header = header;
        this.beginWorkTime = beginWorkTime;
        this.endWorkTime = endWorkTime;
        this.locale = locale;
        this.dateStile = dateStile;
        this.text = text;
    }

    void saveToFile(File file) {
        DateFormat dateFormat = DateFormat.getDateTimeInstance(dateStile, dateStile, locale);
        try (PrintWriter writer=new PrintWriter(new FileWriter(file,true))){
            writer.println(header);
            writer.println();
            writer.print("start time: ");
            writer.println(dateFormat.format(beginWorkTime));
            text.forEach(writer::println);
            writer.print("end time: ");
            writer.println(dateFormat.format(endWorkTime));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
