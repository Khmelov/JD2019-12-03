package by.it.barkovsky.jd02_05;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResManager {
    INSTANCE;

    private String path = "by.it.kharitonenko.jd02_05.lang.translation";
    private ResourceBundle rb;
    private Locale locale;

    ResManager() {
        setLocale(Locale.getDefault());
    }

    void setLocale(Locale locale) {
        this.locale = locale;
        rb = ResourceBundle.getBundle(path, this.locale);
    }

    Locale getLocale() {
        return this.locale;
    }

    String get(String key) {
        return rb.getString(key);
    }
}
