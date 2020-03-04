package by.it.plugatar.calc;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

import static by.it.plugatar.calc.Var.printvar;
import static by.it.plugatar.calc.Var.sortvar;

public class ConsoleRunner {

    public static ResMan lang = ResMan.INSTANCE;
    static {
        lang.setLocale(Locale.getDefault());
    }

    public ResMan getLang() {
        return lang;
    }

    public static void main(String[] args) throws CalcException, IOException {
        String line;
        Scanner scan = new Scanner(System.in);
        Parser parser = new Parser();
        Printer printer = new Printer();
        LogFile log = null;
        log = new LogFile();
        VarFile.load();
        while (true) {
            line = scan.next();
            if (line.equals("end")) {
                log.close();
                break;
            }
            if (line.equals("printvar")) {
                printvar();
            } else if (line.equals("sortvar")) {
                sortvar();
            }
            else if (line.equals("en"))
                lang.setLocale(new Locale("en", "US"));
            else if (line.equals("ru"))
                lang.setLocale(new Locale("ru", "RU"));
            else if (line.equals("be"))
                lang.setLocale(new Locale("be", "BY"));
            else {

                Var result = null;
                try {
                    result = parser.calc(line);//Var
                    //printer.print(result);
                    if (result != null) printer.print(result);
                } catch (CalcException e) {
                    // e.printStackTrace();
                    System.out.println(e.getMessage());
                    log.print(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
