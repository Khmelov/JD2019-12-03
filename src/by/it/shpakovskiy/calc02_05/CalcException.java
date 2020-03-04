package by.it.shpakovskiy.calc02_05;

class CalcException extends Exception {
    public CalcException() {
    }

    public CalcException(String message) {
        super("ERROR: " + message + " ¯\\_(ツ)_/¯");
    }

    public CalcException(String message, Throwable cause) {
        super("ERROR: " + message + " ¯\\_(ツ)_/¯", cause);
    }

    public CalcException(Throwable cause) {
        super(cause);
    }

    public CalcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super("ERROR: " + message + " ¯\\_(ツ)_/¯", cause, enableSuppression, writableStackTrace);
    }
}
