package by.it.shpakovskiy.calc02_06;

import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

class ConsoleRunner {
    private static Parser parser = new Parser();
    private static Printer printer = new Printer();
    private static Logger logger;
    private static ReportBuilder shortReportBuilder = new ReportBuilder(true);
    private static ReportBuilder fullReportBuilder = new ReportBuilder(false);

    public static void main(String[] args) {
        logger = Logger.getInstance();
        Date beginTime = new Date();
        shortReportBuilder.setBeginWorkTime(beginTime);
        fullReportBuilder.setBeginWorkTime(beginTime);
        Scanner scanner = new Scanner(System.in);
        loadVarFromFile();
        while (true) {
            String expression = scanner.nextLine();
            if (expression.equalsIgnoreCase("end")) {
                end(expression);
                break;
            }
            logger.print(expression);
            if (expression.equalsIgnoreCase("printvar")) {
                printVar();
                continue;
            }
            if (expression.equalsIgnoreCase("sortvar")) {
                sortVar();
                continue;
            }
            if (expression.equalsIgnoreCase("ru")) {
                changeLanguage(expression, new Locale("ru", "RU"));
                continue;
            }
            if (expression.equalsIgnoreCase("en")) {
                changeLanguage(expression, Locale.ENGLISH);
                continue;
            }
            if (expression.equalsIgnoreCase("be")) {
                changeLanguage(expression, new Locale("be", "BY"));
                continue;
            }
            printResult(expression);
        }
        saveReportsToFiles();
    }

    static void saveReportsToFiles() {
        Date endTime = new Date();
        Report shortReport = shortReportBuilder.setHeader("Short Report").setEndWorkTime(endTime).build();
        shortReport.saveToFile(new File(System.getProperty("user.dir"),
                "/src/by/it/shpakovskiy/calc02_06/shortReport.txt"));
        Report fullReport = fullReportBuilder.setHeader("Full Report").setEndWorkTime(endTime).build();
        fullReport.saveToFile(new File(System.getProperty("user.dir"),
                "/src/by/it/shpakovskiy/calc02_06/fullReport.txt"));
    }

    static void changeLanguage(String expression, Locale locale) {
        ResMan.changeLanguage(locale);
        logger.print(ResMan.get("languageChanged"));
        shortReportBuilder.setLocale(locale)
                .addOperation(expression, ResMan.get("languageChanged"));
        fullReportBuilder.setLocale(locale)
                .addOperation(expression, ResMan.get("languageChanged"));
        System.out.println(ResMan.get("languageChanged"));
    }

    static void loadVarFromFile() {
        try {
            Var.loadVarFromFile(parser);
            logger.print(ResMan.get("recoveryOfTheVariableList"));
        } catch (CalcException e) {
            System.out.println(e.getMessage());
            String message = e.getMessage();
            shortReportBuilder.addException(e.getSimpleName(), message);
            fullReportBuilder.addException(e.getSimpleName(), message);
            logger.print(message);
        }
    }

    static void printResult(String expression) {
        try {
            Var result = parser.calc(expression);
            printer.print(result);
            String resultStr = result.toString();
            logger.print(resultStr);
            shortReportBuilder.addOperation(expression, resultStr);
            fullReportBuilder.addOperation(expression, resultStr);
        } catch (CalcException e) {
            System.out.println(e.getMessage());
            String message = e.getMessage();
            shortReportBuilder.addException(e.getSimpleName(), message);
            fullReportBuilder.addException(e.getSimpleName(), message);
            logger.print(message);
        }
    }

    static void sortVar() {
        Var.sortAndPrintVars();
        String dataSorted = ResMan.get("dataSorted");
        String stringVars = Var.getStringVars();
        logger.print(dataSorted + "\n" + stringVars);
        shortReportBuilder.addOperation(dataSorted, stringVars);
        fullReportBuilder.addOperation(dataSorted, stringVars);
    }

    static void end(String expression) {
        try {
            Var.saveVarToFile();
        } catch (CalcException e) {
            System.out.println(e.getMessage());
            String message = e.getMessage();
            shortReportBuilder.addException(e.getSimpleName(), message);
            fullReportBuilder.addException(e.getSimpleName(), message);
            logger.print(message);
        }
        logger.print(ResMan.get("saveToFile"));
        System.out.println(ResMan.get("iHopeToSeeYouAgain") + " \u270B");
        logger.print(expression);
    }

    static void printVar() {
        String stringVars = Var.getStringVars();
        System.out.println(stringVars);
        String printVar = ResMan.get("printVar");
        logger.print(printVar + "\n" + stringVars);
        shortReportBuilder.addOperation(printVar,stringVars);
        fullReportBuilder.addOperation(printVar,stringVars);
        try {
            Var.saveVarToFile();
        } catch (CalcException e) {
            System.out.println(e.getMessage());
            String message = e.getMessage();
            shortReportBuilder.addException(e.getSimpleName(), message);
            fullReportBuilder.addException(e.getSimpleName(), message);
            logger.print(message);
        }
    }
}
