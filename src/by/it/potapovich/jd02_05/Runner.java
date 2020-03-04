package by.it.potapovich.jd02_05;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
   //   String changeLanguageWithConsole = scanner.nextLine();


        ResMan res=ResMan.INSTANCE;

        if (args.length==2) {
            Locale locale = new Locale(args[0], args[1]);
            res.setLocale(locale);

            // while (true) {

            while (true) {
                switch (scanner.next()) {
                    case "ru":
                        res.setLocale(new Locale("ru", "RU"));
                        break;
                    case "en":
                        res.setLocale(new Locale("en", "US"));
                        break;
                    case "be":
                        res.setLocale(new Locale("be", "BY"));
                        break;
                    default:
                        res.setLocale(new Locale("en", "US"));
                        break;


                }
                System.out.println(res.get(Messages.WELCOME));
                System.out.println(res.get(User.FIRST_NAME));
                System.out.println(res.get(User.LAST_NAME));
                System.out.println(res.get(Messages.QUESTION));

            }

        }
        //}

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG,res.getLocale());
        String sDate = dateFormat.format(date);

        System.out.println(sDate);

    }
}
