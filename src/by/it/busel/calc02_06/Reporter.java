package by.it.busel.calc02_06;

class Reporter {
    private ReportBuilder reportBuilder;

    void setReportBuilder(ReportBuilder rb) {
        reportBuilder = rb;
    }

    void report() {
        reportBuilder.buildHeading();
        reportBuilder.buildDateTimeBlock();
        reportBuilder.buildRecordsBlock();
        System.out.println(reportBuilder.getReport().toString());
    }
}
