package by.it.busel.calc02_06;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class ShortReportBuilder extends ReportBuilder {
    @Override
    public void buildHeading() {
        String head = blockSeparator + ">>>>>SHORT REPORT" + blockSeparator;
        report.setHeading(head);
    }

    @Override
    public void buildDateTimeBlock() {
        String block = blockSeparator + "LAUNCHING DATA AND TIME:\n" + calculateAndGetLaunchDateTime() + '\n' +
                "TERMINATION DATA AND TIME:\n" + calculateAndGetTerminationDateTime() +
                blockSeparator;
        report.setDateTimeBlock(block);
    }

    private String calculateAndGetLaunchDateTime() {
        Instant launchInstant = reportParts.getLaunchTime();
        ZonedDateTime launchDateTime = ZonedDateTime.ofInstant(launchInstant, ZoneId.systemDefault());
        return launchDateTime.format(DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(ResourcesManager.getLocale()));
    }

    private String calculateAndGetTerminationDateTime() {
        Instant terminationTime = reportParts.getTerminationTime();
        ZonedDateTime terminationDateTime = ZonedDateTime.ofInstant(terminationTime, ZoneId.systemDefault());
        return terminationDateTime.format(DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(ResourcesManager.getLocale()));
    }

    @Override
    public void buildRecordsBlock() {
        StringBuilder sb = new StringBuilder();
        sb.append(blockSeparator);
//        LinkedHashMap<Instant, ArrayList<String>> inputOutputRecords = reportParts.getInputOutputRecords();
        ArrayList<String> errorTypes = reportParts.getErrorTypes();
        int index = 0;
        String separator = "";
        for (Instant instant : reportParts.getInputOutputRecords().keySet()) {
            sb.append(separator).append(calculateAndGetDateTime(instant)).append(": ")
                    .append(errorTypes.get(index++));
            separator = "\n";
        }
        sb.append(blockSeparator);
        report.setRecordsBlock(sb.toString());
    }

    private String calculateAndGetDateTime(Instant dateTime) {
        ZonedDateTime keyTime = ZonedDateTime.ofInstant(dateTime, ZoneId.systemDefault());
        return keyTime.format(DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(ResourcesManager.getLocale()));
    }
}
