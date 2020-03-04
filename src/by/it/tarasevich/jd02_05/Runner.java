package by.it.tarasevich.jd02_05;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Runner {
    public static void main(String[] args) {
        Locale rus = new Locale("ru", "RU");
        Locale bel = new Locale("be", "BY");
        Locale eng = new Locale("en", "US");

        DateFormat df1 = DateFormat.getDateInstance(DateFormat.FULL, new Locale("ru", "RU"));
        DateFormat df2 = DateFormat.getDateInstance(DateFormat.FULL, new Locale("be", "BY"));
        DateFormat df3 = DateFormat.getDateInstance(DateFormat.FULL, new Locale("en", "US"));

        //String p = "by.it.tarasevich.jd02_05.text.properties";
        //тут ошибка, явно лишнее слово properties т.к. нужен reference к ресурсам
        //а вот, то что ресурсы через точку, а не через слеш это как раз правильно
        String p = "by.it.tarasevich.jd02_05.text";
        //String p = System.getProperty("user.dir") + "/src/by/it/tarasevich/jd02_05/text";

        ResourceBundle s1 = ResourceBundle.getBundle(p, rus);
        ResourceBundle s2 = ResourceBundle.getBundle(p, bel);
        ResourceBundle s3 = ResourceBundle.getBundle(p, eng);

        //каждый ключ читается отдельно, их склеивать нельзя,
        //сначала нужно извлечь, а потом использовать
        //например, так вот будет работать
        String str1 =  String.format("%s, %s ", s1.getString("user.name"), s1.getString("message.welcome"));
        //или так даже лучше, можно вставить пробел
        String str2 = String.format("%s, %s ", s2.getString("user.name"), s2.getString("message.welcome"));
        //а так не будет
        String str3 = String.format("%s, %s ", s3.getString("user.name"), s3.getString("message.welcome"));

        System.out.println(str1 + df1.format(new Date()));
        System.out.println(str2 + df2.format(new Date()));
        System.out.println(str3 + df3.format(new Date()));
    }
}
