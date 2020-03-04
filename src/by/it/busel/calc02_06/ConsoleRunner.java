package by.it.busel.calc02_06;

import java.util.Scanner;

class ConsoleRunner {
    private static final ReportParts.Builder reportPartsBuilder = new ReportParts.Builder();
    private static Scanner scanner = new Scanner(System.in);
    private static Parser parser = new Parser();
    private static Printer printer = new Printer();

    /*
        a static block which initializes necessary resources in order to run the current "CALC" program
     */
    static {
        LoggerSingleton.getInstance();
        new Storage();
        new ResourcesManager();
    }

    public static void main(String[] args) {
//        Storage storage = new Storage();
//        ResourcesManager rManager = new ResourcesManager();
        while (true) {
            String varExpression = scanner.nextLine();
            if (!varExpression.equals("end") && !varExpression.equals("конец")) {
                if (ConsoleCommands.call(varExpression)) continue;
                performCalculation(varExpression);
            } else {
                ReportParts reportParts = reportPartsBuilder.build();
                ReportBuilder reportBuilder = createReportBuilder(reportParts);
//                if (scanner.nextLine().equals("y")) {
//                    reportBuilder = new FullReportBuilder();
//                } else {
//                    reportBuilder = new ShortReportBuilder();
//                }
//                reportBuilder.initializeReportingProcessWith(reportParts);
                Reporter reporter = createReport(reportBuilder);
//                        new Reporter();
//                reporter.setReportBuilder(reportBuilder);
                reporter.report();
                break;
            }

        }
    }

    static void performCalculation(String varExpression) {
        try {
            Var result = parser.calc(varExpression.replace("\\s+", ""));
            printer.printAndSave(varExpression, result.toString());
            reportPartsBuilder.addInputOutputRecords
                    (varExpression, result.toString(), "NOT AN EXCEPTION");
        } catch (CalcException e) {
            printer.printAndSave(varExpression, e.getMessage());
            reportPartsBuilder.addInputOutputRecords
                    (varExpression, e.getMessage(), e.getClass().getSimpleName());
        }
    }

    private static ReportBuilder createReportBuilder(ReportParts reportParts) {
        System.out.println("Do you want a full report?\nPlease, press \"y\" if yes.\nOtherwise a short one will be printed");
        ReportBuilder reportBuilder = scanner.nextLine().equals("y") ? new FullReportBuilder() : new ShortReportBuilder();
        reportBuilder.initializeReportingProcessWith(reportParts);
        return reportBuilder;
    }

    private static Reporter createReport(ReportBuilder reportBuilder) {
        Reporter reporter = new Reporter();
        reporter.setReportBuilder(reportBuilder);
        return reporter;
    }
}
