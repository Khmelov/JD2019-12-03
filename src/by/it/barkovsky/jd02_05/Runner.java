package by.it.barkovsky.jd02_05;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        boolean closeProgram = false;
        Scanner sc = new Scanner(System.in);
        ResManager lang = ResManager.INSTANCE;
        if (args.length == 2) {
            lang.setLocale(new Locale(args[0], args[1]));
        } else
            lang.setLocale(Locale.ENGLISH);

        for (; ; ) {
            DateFormat date = DateFormat.getDateInstance(DateFormat.FULL, lang.getLocale());
            Date d = new Date();
            String stringDate = date.format(d);
            System.out.println(stringDate);
            System.out.println(lang.get(Messages.welcome));
            System.out.printf("%s %s\n",
                    lang.get(User.NAME)
            );
            System.out.println(lang.get(Messages.INFO));
            String locale = sc.nextLine();
            switch (locale) {
                case "en":
                    lang.setLocale(new Locale("en", "US"));
                    break;
                case "ru":
                    lang.setLocale(new Locale("ru", "RU"));
                    break;
                case "be":
                    lang.setLocale(new Locale("be", "BY"));
                    break;
                case "end":
                    closeProgram = true;
                    break;
                default:
                    break;
            }
            if (closeProgram) break;
        }
    }
}
