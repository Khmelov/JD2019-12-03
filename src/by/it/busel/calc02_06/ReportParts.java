package by.it.busel.calc02_06;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;

class ReportParts {
    private Clock clock;
    private Instant launchTime;
    private Instant terminationTime;
    private LinkedHashMap<Instant, ArrayList<String>> inputOutputRecords;
    private ArrayList<String> errorTypes;
//    private LinkedHashMap<Instant, String> outputRecords;

    public Instant getLaunchTime() {
        return launchTime;
    }

    public Instant getTerminationTime() {
        return terminationTime;
    }

    public LinkedHashMap<Instant, ArrayList<String>> getInputOutputRecords() {
        return inputOutputRecords;
    }

    public ArrayList<String> getErrorTypes() {
        return errorTypes;
    }

    public static class Builder {
        private ReportParts reportParts;

        public Builder() {
            reportParts = new ReportParts();
            reportParts.clock = Clock.systemDefaultZone();
            reportParts.launchTime = reportParts.clock.instant();
            reportParts.inputOutputRecords = new LinkedHashMap<>();
            reportParts.errorTypes = new ArrayList<>();
        }

        public Builder addInputOutputRecords(String input, String output, String errorType) {
            ArrayList<String> inputOutput = new ArrayList<>();
            inputOutput.add(input);
            inputOutput.add(output);
            reportParts.inputOutputRecords.put(reportParts.clock.instant(), inputOutput);
            reportParts.errorTypes.add(errorType);
            return this;
        }

        public ReportParts build() {
            reportParts.terminationTime = reportParts.clock.instant();
            return reportParts;
        }
    }
}
