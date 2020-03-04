package by.it.potapovich.jd02_05;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResMan {
    INSTANCE;

    private String locationRes = "by.it.potapovich.jd02_05.messages";

    private ResourceBundle resourceBundle;


    ResMan() {
        setLocale( Locale.ENGLISH);

    }
    public Locale getLocale(){
        return resourceBundle.getLocale();
    }


    void setLocale(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(locationRes, locale);
    }


    String get(String key) {
        return resourceBundle.getString(key);
    }


}
