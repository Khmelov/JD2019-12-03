package by.it.ban.calc;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import static by.it.ban.calc.Var.printvar;
import static by.it.ban.calc.Var.sortvar;

public class ConsoleRunner {

    static {
        ResManager.setLocale(Locale.getDefault());
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        Printer printer = new Printer();
        LogFile log;
        log = LogFile.getLogger();
        try {
            VarFile.load();
        } catch (CalcException e) {
            System.out.println(e.getMessage());
            log.print(e.getMessage());
        }
        while (true) {
            String expression = scanner.next();
            if (expression.equals("end")) {
 //               log.close();
                break;
            }
            if (expression.equals("printvar")) {
                printvar();
            } else if (expression.equals("sortvar")) {
                sortvar();
            }
            else if (expression.equals("en"))
                ResManager.setLocale(new Locale("en", "US"));
            else if (expression.equals("ru"))
                ResManager.setLocale(new Locale("ru", "RU"));
            else if (expression.equals("be"))
                ResManager.setLocale(new Locale("be", "BY"));
            else {
                Var result = null;
                try {
                    log.print(expression);
                    result = parser.calc(expression);
                    if (result != null) printer.print(result);
                } catch (CalcException e) {
                    System.out.println(e.getMessage());
                    log.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
