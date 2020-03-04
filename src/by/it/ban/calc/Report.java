package by.it.ban.calc;

abstract class Report {
    abstract void printTitle();
    abstract void printStart();
    abstract void printEnd();
    abstract void printOperation(String string);
    abstract void printResult(String string);
    abstract void printError(Exception exp);
}
