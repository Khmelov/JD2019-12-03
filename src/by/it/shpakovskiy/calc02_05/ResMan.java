package by.it.shpakovskiy.calc02_05;

import java.util.Locale;
import java.util.ResourceBundle;

class ResMan {
    private static String textResPath = "by.it.shpakovskiy.calc02_05.res.text";
    private static ResourceBundle rb = ResourceBundle.getBundle(textResPath);
    private static Locale locale = Locale.getDefault();

    private ResMan() {
    }

    static void changeLanguage(Locale loc) {
        rb = ResourceBundle.getBundle(textResPath, loc);
        locale = loc;
        Locale.setDefault(locale);
    }

    static String get(String key) {
        return rb.getString(key);
    }

    static Locale getLocale() {
        return locale;
    }
}
