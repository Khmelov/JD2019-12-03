package by.it.shpakovskiy.calc02_06;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Tdsfhhfhver {
    public static void main(String[] args) {
        Date date=new Date();
        DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.ENGLISH);
        System.out.println(dateTimeInstance.format(date));
        DateFormat dateTimeInstance1 = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.ENGLISH);
        System.out.println(dateTimeInstance1.format(date));
        DateFormat dateTimeInstance2 = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.ENGLISH);
        System.out.println(dateTimeInstance2.format(date));
    }
}
