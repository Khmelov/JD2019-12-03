package by.it.popkov.calc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

class LongReport implements Report {
    @Override
    public void writeHead() {
        System.out.println("===========");
        System.out.println("Long Report");
        System.out.println("===========");
    }

    @Override
    public void writeTimeStart(LocalDateTime startTime) {
        System.out.println("Start time: " + startTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
    }

    @Override
    public void writeTimeFinish(LocalDateTime finishTime) {
        System.out.println("Finish time: " + finishTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
    }

    @Override
    public void writeOperation(String logOperation) {
        System.out.println("-----------------------------");
        System.out.println("List of operation:");
        try {
            Files.lines(Paths.get(logOperation)).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("No operation");
        }
        System.out.println("-----------------------------");
    }

    @Override
    public void writeErrors(String logError) {
        System.out.println("-----------------------------");
        System.out.println("List of errors:");
        try {
            Files.lines(Paths.get(logError)).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("No errors :-)");
        }
        System.out.println("-----------------------------");
    }
}
