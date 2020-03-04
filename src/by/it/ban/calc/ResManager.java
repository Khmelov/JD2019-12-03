package by.it.ban.calc;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResManager {
    INSTANCE;

    private static ResourceBundle rb;
    private static Locale locale;

    ResManager() {
        setLocale(Locale.getDefault());
    }

    static void setLocale(Locale newLocale) {
        locale = newLocale;
        String path = "by.it.ban.calc.lang.translation";
        rb = ResourceBundle.getBundle(path, locale);
    }

    Locale getLocale() {
        return locale;
    }

    static String get(String key) {
        return rb.getString(key);
    }
}