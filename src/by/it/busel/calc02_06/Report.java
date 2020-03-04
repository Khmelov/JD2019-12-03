package by.it.busel.calc02_06;

class Report {

    private String heading = "";

    private String dateTimeBlock = "";

    private String recordsBlock = "";

    void setHeading(String heading) {
        this.heading = heading;
    }

    void setDateTimeBlock(String dateTimeBlock) {
        this.dateTimeBlock = dateTimeBlock;
    }

    void setRecordsBlock(String recordsBlock) {
        this.recordsBlock = recordsBlock;
    }

    @Override
    public String toString() {
        return heading + dateTimeBlock + recordsBlock;
    }
}
