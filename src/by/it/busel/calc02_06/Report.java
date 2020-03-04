package by.it.busel.calc02_06;

import java.time.Clock;
import java.time.Instant;
import java.util.LinkedHashMap;

class Report {
    private Clock clock;
    private Instant launchTime;
    private Instant terminationTime;
    private LinkedHashMap<Instant, String> inputRecords;
    private LinkedHashMap<Instant, String> outputRecords;

    public Instant getLaunchTime() {
        return launchTime;
    }

    public Instant getTerminationTime() {
        return terminationTime;
    }

    public LinkedHashMap<Instant, String> getInputRecords() {
        return inputRecords;
    }

    public LinkedHashMap<Instant, String> getOutputRecords() {
        return outputRecords;
    }

    public static class Builder {
        private Report report;

        public Builder() {
            report = new Report();
            report.clock = Clock.systemDefaultZone();
            report.launchTime = report.clock.instant();
            report.inputRecords = new LinkedHashMap<>();
            report.outputRecords = new LinkedHashMap<>();
        }

        public Builder addInputOutputRecords(String input, String output) {
            this.addInputRecord(input);
            this.addOutputRecord(output);
            return this;
        }

        private void addOutputRecord(String output) {
            report.outputRecords.put(report.clock.instant(), output);
        }

        private void addInputRecord(String input) {
            report.inputRecords.put(report.clock.instant(), input);
        }

        public Report build() {
            report.terminationTime = report.clock.instant();
            return report;
        }
    }

    //    public static void main(String[] args) {
//        Report.Builder reportBuilder = new Report.Builder();
//        reportBuilder.addInputOutputRecords("Привет", "Пока");
//        Report report = reportBuilder.build();
//        Instant launchTime = report.getLaunchTime();
//        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(launchTime, ZoneId.systemDefault());
//        System.out.println(zonedDateTime.format
//                (DateTimeFormatter.ofLocalizedDateTime
//                        (FormatStyle.FULL, FormatStyle.FULL).withLocale(new Locale("be", "BY"))));
//        System.out.println(zonedDateTime.format
//                (DateTimeFormatter.ofLocalizedDateTime
//                        (FormatStyle.FULL, FormatStyle.FULL).withLocale(new Locale("ru", "RU"))));
//        System.out.println(zonedDateTime.format
//                (DateTimeFormatter.ofLocalizedDateTime
//                        (FormatStyle.FULL, FormatStyle.FULL).withLocale(new Locale("en", "GB"))));
//        System.out.println(zonedDateTime.format
//                (DateTimeFormatter.ofLocalizedDateTime
//                        (FormatStyle.SHORT, FormatStyle.SHORT).withLocale(new Locale("en", "GB"))));
//        System.out.println(zonedDateTime.format
//                (DateTimeFormatter.ofLocalizedDateTime
//                        (FormatStyle.SHORT, FormatStyle.SHORT).withLocale(new Locale("be", "BY"))));
//        System.out.println(zonedDateTime.format
//                (DateTimeFormatter.ofLocalizedDateTime
//                        (FormatStyle.SHORT, FormatStyle.SHORT).withLocale(new Locale("ru", "RU"))));
//
//
//    }
}
