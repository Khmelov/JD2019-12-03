package by.it.busel.calc02_06;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FullReportBuilder extends ReportBuilder {

    @Override
    public void buildHeading() {
        String head = blockSeparator + ">>>>>FULL REPORT" + blockSeparator;
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
                .ofLocalizedDateTime(FormatStyle.FULL)
                .withLocale(ResourcesManager.getLocale()));
    }

    private String calculateAndGetTerminationDateTime() {
        Instant terminationTime = reportParts.getTerminationTime();
        ZonedDateTime terminationDateTime = ZonedDateTime.ofInstant(terminationTime, ZoneId.systemDefault());
        return terminationDateTime.format(DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.FULL)
                .withLocale(ResourcesManager.getLocale()));
    }

    @Override
    public void buildRecordsBlock() {
        StringBuilder sb = new StringBuilder();
        sb.append(blockSeparator);
        LinkedHashMap<Instant, ArrayList<String>> inputOutputRecords = reportParts.getInputOutputRecords();
        String separator = "\n";
        for (Map.Entry<Instant, ArrayList<String>> entrySet : inputOutputRecords.entrySet()) {
            ArrayList<String> inputOutput = entrySet.getValue();
            sb.append(separator)
                    .append(calculateAndGetDateTime(entrySet)).append('\n')
                    .append(ResourcesManager.get(Message.PRINTER_INPUT)).append('\n')
                    .append(inputOutput.get(0)).append('\n')
                    .append(ResourcesManager.get(Message.PRINTER_OUTPUT)).append('\n')
                    .append(inputOutput.get(1)).append('\n');
            separator = recordSeparator;
        }
        sb.append(blockSeparator);
        report.setRecordsBlock(sb.toString());

    }

    private String calculateAndGetDateTime(Map.Entry<Instant, ArrayList<String>> entrySet) {
        Instant key = entrySet.getKey();
        ZonedDateTime keyTime = ZonedDateTime.ofInstant(key, ZoneId.systemDefault());
        return keyTime.format(DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.FULL)
                .withLocale(ResourcesManager.getLocale()));
    }
}
