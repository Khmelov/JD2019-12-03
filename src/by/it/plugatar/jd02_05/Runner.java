package by.it.plugatar.jd02_05;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        String s="";
        DateFormat df;
        ResMan resMan = ResMan.INSTANCE;
        if (args.length == 0) {
            resMan.setLocale("En","US");
            s="en";
            System.out.println(s);
        }
        else  {
            resMan.setLocale(args[0], args[1]);
            s=args[0];

        }
        printConsole(resMan);
        printDate(s);
        System.out.println("Введите необходимый язык(\"ru\", \"en\", \"be\").");
        Scanner scanner= new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("end")){
            switch (line){
                case "ru":
                    resMan.setLocale("RU","RU");
                    s="ru";
                    break;
                case "en":
                    resMan.setLocale("EN","US");
                    s="en";
                    break;
                case "be":
                    resMan.setLocale("BE","BY");
                    s="be";
                    break;
                default:
                    System.out.println("не найдено");
            }
            printConsole(resMan);
            printDate(s);
        }
    }

    private static void printDate(String dateLanguage) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale(dateLanguage));
        String s = df.format(new Date());
        System.out.println(s);
    }

    private static void printConsole(ResMan resMan) {
        System.out.println(resMan.get(Messages.WELCOME));
        System.out.println(resMan.get(Messages.QUESTION));
        System.out.println(resMan.get(User.FIRST_NAME)+" "+resMan.get(User.LAST_NAME));
    }

}
