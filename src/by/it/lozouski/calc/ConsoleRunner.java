package by.it.lozouski.calc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

class ConsoleRunner {
    static ChangeLangService langService = ChangeLangService.START;
    static LogSingleton logSingleton = LogSingleton.getInstance();


    public static void main(String[] args) {
        Scanner sc1 = new Scanner(System.in);
        Parser parser = new Parser();
        Printer printer = new Printer();
        System.out.println(langService.get(Messages.MES_CALC_START));
        Logging.logFileRecord(langService.get(Log.LOG_PROG_START));
        logSingleton.logFileRecord(langService.get(Log.LOG_PROG_START));//jd02-06
        try {
            if (Files.exists(Paths.get(VarFile.getVarFile()))){
                VarFile.load(parser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        label:
        while (true) {
            String inputLine = sc1.nextLine();
            switch (inputLine) {
                case "end":
                    Logging.logFileRecord(langService.get(Log.LOG_PROG_FINISH));
                    logSingleton.logFileRecord(langService.get(Log.LOG_PROG_FINISH));//jd02-06
                    break label;
                case "printvar":
                    Var.printStorageVar();
                    break;
                case "en":
                    langService.setLocale(new Locale("en", "US"));
                    System.out.println(langService.get(Messages.MES_CURRENT_LANG));
                    break;
                case "ru":
                    langService.setLocale(new Locale("ru", "RU"));
                    System.out.println(langService.get(Messages.MES_CURRENT_LANG));
                    break;
                case "be":
                    langService.setLocale(new Locale("be", "BY"));
                    System.out.println(langService.get(Messages.MES_CURRENT_LANG));
                    break;
                default:
                    try {
                        Logging.logFileRecord(inputLine);
                        logSingleton.logFileRecord(inputLine);//jd02-06
                        Var result = parser.calculate(inputLine);
                        printer.print(result);
                        Logging.logFileRecord(langService.get(Log.LOG_RESULT) + result.toString());
                        logSingleton.logFileRecord(langService.get(Log.LOG_RESULT) + result.toString());//jd02-06
                    } catch (CalcException e) {
                        Logging.logFileRecord(e.getMessage());
                        logSingleton.logFileRecord(e.getMessage());//jd02-06
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}
