package by.it.shpakovskiy.calc02_05;

import java.util.Locale;
import java.util.Scanner;

class ConsoleRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        Printer printer = new Printer();
        Logger logger = new Logger();
        loadVarFromFile(parser, logger);
        while (true) {
            String expression = scanner.nextLine();
            if (expression.equalsIgnoreCase("end")) {
                end(logger, expression);
                break;
            }
            logger.print(expression);
            if (expression.equalsIgnoreCase("printvar")) {
                printVar(logger);
                continue;
            }
            if (expression.equalsIgnoreCase("sortvar")) {
                sortVar(logger);
                continue;
            }
            if (expression.equalsIgnoreCase("ru")) {
                changeLanguage(logger, new Locale("ru", "RU"));
                continue;
            }
            if (expression.equalsIgnoreCase("en")) {
                changeLanguage(logger, Locale.ENGLISH);
                continue;
            }
            if (expression.equalsIgnoreCase("be")) {
               changeLanguage(logger, new Locale("be", "BY"));
                continue;
            }
            printResult(parser, printer, logger, expression);
        }
    }

    static void changeLanguage(Logger logger, Locale locale) {
        ResMan.changeLanguage(locale);
        logger.print(ResMan.get("languageChanged"));
        System.out.println(ResMan.get("languageChanged"));
    }

    static void loadVarFromFile(Parser parser, Logger logger) {
        try {
            Var.loadVarFromFile(parser);
            logger.print(ResMan.get("recoveryOfTheVariableList"));
        } catch (CalcException e) {
            System.out.println(e.getMessage());
            logger.print(e.getMessage());
        }
    }

    static void printResult(Parser parser, Printer printer, Logger logger, String expression) {
        try {
            Var result = parser.calc(expression);
            printer.print(result);
            logger.print(result.toString());
        } catch (CalcException e) {
            System.out.println(e.getMessage());
            logger.print(e.getMessage());
        }
    }

    static void sortVar(Logger logger) {
        Var.sortAndPrintVars();
        logger.print(ResMan.get("dataSorted") + "\n" + Var.getStringVars());
    }

    static void end(Logger logger, String expression) {
        try {
            Var.saveVarToFile();
        } catch (CalcException e) {
            System.out.println(e.getMessage());
            logger.print(e.getMessage());
        }
        logger.print(ResMan.get("saveToFile"));
        System.out.println(ResMan.get("iHopeToSeeYouAgain") + " \u270B");
        logger.print(expression);
    }

    static void printVar(Logger logger) {
        String stringVars = Var.getStringVars();
        System.out.println(stringVars);
        logger.print(ResMan.get("printVar") + "\n" + stringVars);
        try {
            Var.saveVarToFile();
        } catch (CalcException e) {
            System.out.println(e.getMessage());
            logger.print(e.getMessage());
        }
    }
}
