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

    Instant getLaunchTime() {
        return launchTime;
    }

    Instant getTerminationTime() {
        return terminationTime;
    }

    LinkedHashMap<Instant, ArrayList<String>> getInputOutputRecords() {
        return inputOutputRecords;
    }

    ArrayList<String> getErrorTypes() {
        return errorTypes;
    }

    static class Builder {
        private ReportParts reportParts;

        Builder() {
            reportParts = new ReportParts();
            reportParts.clock = Clock.systemDefaultZone();
            reportParts.launchTime = reportParts.clock.instant();
            reportParts.inputOutputRecords = new LinkedHashMap<>();
            reportParts.errorTypes = new ArrayList<>();
        }

        Builder addInputOutputRecords(String input, String output, String errorType) {
            ArrayList<String> inputOutput = new ArrayList<>();
            inputOutput.add(input);
            inputOutput.add(output);
            reportParts.inputOutputRecords.put(reportParts.clock.instant(), inputOutput);
            reportParts.errorTypes.add(errorType);
            return this;
        }

        ReportParts build() {
            reportParts.terminationTime = reportParts.clock.instant();
            return reportParts;
        }
    }
}
