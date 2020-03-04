package by.it.kuzmichalex.calc_jd02_06;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

class LanguageManager {
    static private ResourceManager resource;
    static private Locale locale;
    static private Calendar calendar;
    static private DateFormat dateFormat;
    static private DateFormat timeFormat;

    static {
        Locale locale = new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
        resource = ResourceManager.INSTANCE;
        resource.setLocale(locale);
        calendar = Calendar.getInstance(locale);
        dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        timeFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM,locale);
    }

    /**
     * set language
     *
     * @param language = en, by, ru
     */
    static void setLanguage(String language) {
        if (language.toLowerCase().equals("en")) {
            locale = new Locale("en", "US");
            resource.setLocale(locale);
            calendar = Calendar.getInstance(locale);
            dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        } else if (language.toLowerCase().equals("by") || language.toLowerCase().equals("be")) {
            locale = new Locale("be", "BY");
            resource.setLocale(locale);
            calendar = Calendar.getInstance(locale);
            dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        } else if (language.toLowerCase().equals("ru")) {
            locale = new Locale("ru", "RU");
            resource.setLocale(locale);
            calendar = Calendar.getInstance(locale);
            dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        }
    }

    static String get(String str) {
        return resource.get(str)+" ";
    }

    static String getLocaleDate(){
        return dateFormat.format(calendar.getTime());
    }
    static String getLocaleTime(){
        return timeFormat.format(calendar.getTime());
    }

}
