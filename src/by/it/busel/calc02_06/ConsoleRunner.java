package by.it.busel.calc02_06;

import java.util.Scanner;

class ConsoleRunner {
    static ReportParts.Builder reportPartsBuilder = new ReportParts.Builder();
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

    static void performCalculation(String varExpression) {
        try {
            Var result = parser.calc(varExpression.replace("\\s+", ""));
            printer.printAndSave(varExpression, result.toString());
            reportPartsBuilder = reportPartsBuilder.addInputOutputRecords
                    (varExpression, result.toString(), ResourcesManager.get(Message.RUNNER_NOT_EXCEPTION_TYPE));
        } catch (CalcException e) {
            printer.printAndSave(varExpression, e.getMessage());
            reportPartsBuilder = reportPartsBuilder.addInputOutputRecords
                    (varExpression, e.getMessage(), e.getClass().getSimpleName());
        }
    }

    private static boolean askIfFullReport(Scanner scanner) {
        System.out.println(ResourcesManager.get(Message.RUNNER_ASK_IF_FULL));
        return scanner.nextLine().equals("y");
    }

    private static ReportBuilder createReportBuilder(ReportParts reportParts, boolean isFullReport) {
        ReportBuilder reportBuilder = isFullReport ? new FullReportBuilder() : new ShortReportBuilder();
        reportBuilder.initializeReportingProcessWith(reportParts);
        return reportBuilder;
    }

    private static Reporter createReport(ReportBuilder reportBuilder) {
        Reporter reporter = new Reporter();
        reporter.setReportBuilder(reportBuilder);
        return reporter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String varExpression = scanner.nextLine();
            if (!varExpression.equals("end") && !varExpression.equals("конец")) {
                if (ConsoleCommands.call(varExpression)) continue;
                performCalculation(varExpression);
            } else {
                ReportParts reportParts = reportPartsBuilder.build();
                boolean isFullReport = askIfFullReport(scanner);
                ReportBuilder reportBuilder = createReportBuilder(reportParts, isFullReport);
                Reporter reporter = createReport(reportBuilder);
                reporter.report();
                break;
            }
        }
    }
}
