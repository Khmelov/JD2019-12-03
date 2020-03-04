package by.it.busel.calc02_06;

class Report {

    private String heading = "";

    private String dateTimeBlock = "";

    private String recordsBlock = "";

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public void setDateTimeBlock(String dateTimeBlock) {
        this.dateTimeBlock = dateTimeBlock;
    }

    public void setRecordsBlock(String recordsBlock) {
        this.recordsBlock = recordsBlock;
    }

    @Override
    public String toString() {
        return heading + dateTimeBlock + recordsBlock;
//        return "Report{" +
//                "heading='" + heading + '\'' +
//                ", dateTimeBlock='" + dateTimeBlock + '\'' +
//                ", recordsBlock='" + recordsBlock + '\'' +
//                '}';
    }
}
