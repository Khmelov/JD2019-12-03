package by.it.plugatar.calc;

public class CalcException extends Exception {
    public CalcException() {
    }

    public CalcException(String message) {
        super(ConsoleRunner.lang.get(ErrorMessages.ERR)+" "+ message);
    }

    public CalcException(String message, Throwable cause) {
        super(ConsoleRunner.lang.get(ErrorMessages.ERR)+" "+ message, cause);
    }

    public CalcException(Throwable cause) {
        super(cause);
    }

    public CalcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(ConsoleRunner.lang.get(ErrorMessages.ERR)+" "+ message, cause, enableSuppression, writableStackTrace);
    }
}
