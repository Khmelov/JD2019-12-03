package by.it.popkov.calc;

import java.time.LocalDateTime;

interface Report {
    void writeHead();
    void writeTimeStart(LocalDateTime startTime);
    void writeTimeFinish(LocalDateTime finishTime);
    void writeOperation(String logOperation);
    void writeErrors(String logError);
}
