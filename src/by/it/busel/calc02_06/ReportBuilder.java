package by.it.busel.calc02_06;

abstract class ReportBuilder {
    protected static String blockSeparator = "\n--------------------------------------------------\n";
    protected static String recordSeparator = "-------------------------\n";
    protected Report report;
    protected ReportParts reportParts;

    Report getReport() {
        return report;
    }

    void initializeReportingProcessWith(ReportParts reportParts) {
        this.reportParts = reportParts;
        report = new Report();
    }

    public abstract void buildHeading();

    public abstract void buildDateTimeBlock();

    public abstract void buildRecordsBlock();
}
