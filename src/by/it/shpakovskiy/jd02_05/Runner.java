package by.it.shpakovskiy.jd02_05;

import java.util.Locale;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        Locale localeBLR = new Locale("be", "BY");
        Locale localeRU = new Locale("ru", "RU");
        Locale localeUS = Locale.US;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select language (1-belorussian, 2-russian, 3-english)");
        String command = sc.nextLine();
        if(command.equals("1")){
            TextRes.changeLanguage(localeBLR);
        }else if (command.equals("2")){
            TextRes.changeLanguage(localeRU);
        }else {
            if (!command.equals("3")) MyPrinter.printUnknownCommand(localeUS);
            TextRes.changeLanguage(localeUS);
        }
        if (args.length > 1) MyPrinter.changeLanguage(new Locale(args[0], args[1]));
        MyPrinter.printHello(Locale.getDefault());
        MyPrinter.printUserName(Locale.getDefault());
        MyPrinter.printDateTime(Locale.getDefault());
        while (true) {
            command = sc.nextLine();
            switch (command) {
                case "be":
                case "BE":
                    MyPrinter.changeLanguage(localeBLR);
                    break;
                case "ru":
                case "RU":
                    MyPrinter.changeLanguage(localeRU);
                    break;
                case "en":
                case "EN":
                    MyPrinter.changeLanguage(localeUS);
                    break;
                case "end":
                case "END":
                    return;
                case "time":
                case "date":
                case "TIME":
                case "DATE":
                    MyPrinter.printDateTime(Locale.getDefault());
                    break;
                default:
                    MyPrinter.printUnknownCommand(Locale.getDefault());
            }
        }
    }
}
