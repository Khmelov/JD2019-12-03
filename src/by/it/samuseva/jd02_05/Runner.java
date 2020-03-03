package by.it.samuseva.jd02_05;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Runner {
    private static String language;
    private static String country;
    public static void main(String[] args) {

        ResMan res = ResMan.INSTANCE;
        Scanner scan = new Scanner(System.in);
        printMessagesInLocale(res, "en", "US");

        while (true) {
            System.out.println(res.get(Message.LANGUAGE));
            String line = scan.nextLine();
            if (line.equals("end")) break;
            try {
                chooseLanguage(res,line);
                printMessagesInLocale(res, language, country);
                System.out.println(res.get(Message.LEFT_OUT) +" \"end\".");
            } catch (MyException e) {
                System.err.println(res.get(Message.EXCEPTION) + " " + res.get(Message.MESSAGE));
                continue;
            }


        }
    }

    private static void chooseLanguage(ResMan res, String line) throws MyException {
        if (line==null) throw new MyException();

        switch (line) {
            case "end": return;
            case "be": {
                language = "be";
                country = "BY";
                break;
            }
            case "ru": {
                language = "ru";
                country = "Ru";
                break;
            }
            case "en": {
                language = "en";
                country = "US";
                break;
            }
            default: throw new MyException(res.get(Message.EXCEPTION));
        }

    }

    private static void printMessagesInLocale(ResMan res, String language, String country) {
        Locale locale = new Locale(language, country);
        res.setLocale(locale);
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        System.out.println(res.get(Message.WELCOME));
        System.out.print (res.get(User.FIRST_NAME) +" ");
        System.out.println(res.get(User.LAST_NAME));
        System.out.println(res.get(Message.QUESTION));
        System.out.println(res.get(Message.DATE) + " " + df.format(new Date()));
    }
}
