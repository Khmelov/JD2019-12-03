package by.it.plugatar.calc;
import java.util.Locale;
import java.util.ResourceBundle;

public enum ResMan {
    INSTANCE;

    private ResourceBundle rb;
    private Locale locale;

    ResMan() {
        setLocale(Locale.getDefault());
    }

    void setLocale(Locale locale) {
        this.locale = locale;
        String path = "by.it.plugatar.calc.properties.messages";
        rb = ResourceBundle.getBundle(path, this.locale);
    }
    Locale getLocale() {
        return this.locale;
    }

    public String get(String key) {
        return rb.getString(key);
    }
}
