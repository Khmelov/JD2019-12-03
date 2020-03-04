package by.it.kuzmichalex.calc_jd02_06;

import java.util.Locale;
import java.util.ResourceBundle;

enum ResourceManager {
    INSTANCE;

    private String locationRes = "by.it.kuzmichalex.calc_jd02_06.properties.Messages";

    private ResourceBundle resourceBundle;

    ResourceManager() {
        setLocale(Locale.ENGLISH);
    }

    void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(locationRes, locale);
    }

    String get(String key) {
        return resourceBundle.getString(key);
    }

}