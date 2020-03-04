package by.it.shpakovskiy.calc02_06;

import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

class ReportBuilder {
    private String header;
    private Date beginWorkTime;
    private Date endWorkTime;
    private boolean shortStyle;
    private Locale locale = ResMan.getLocale();
    private LinkedList<String> lines = new LinkedList<>();

    public ReportBuilder(boolean shortStyle) {
        this.shortStyle = shortStyle;
    }

    ReportBuilder setHeader(String header){
        this.header = header;
        return this;
    }

    ReportBuilder setBeginWorkTime(Date beginWorkTime) {
        this.beginWorkTime = beginWorkTime;
        return this;
    }

    ReportBuilder setEndWorkTime(Date endWorkTime) {
        this.endWorkTime = endWorkTime;
        return this;
    }

    ReportBuilder setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    ReportBuilder addOperation(String operation, String result){
        lines.add("Operation: " + operation + "; Result: " + result);
        return this;
    }

    ReportBuilder addException(String type, String detail){
        if (shortStyle) lines.add("ERROR: " + type);
        else lines.add("ERROR: " + type + " - " +detail);
        return this;
    }

    Report build(){
        return new Report(header, beginWorkTime, endWorkTime, locale, dateStyle(), lines);
    }

    private int dateStyle() {
        return shortStyle?DateFormat.SHORT:DateFormat.MEDIUM;
    }
}
