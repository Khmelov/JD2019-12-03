package by.it.samuseva.jd02_05;

public class MyException extends Exception implements Message{
    public MyException() {
    }

    public MyException(String message) {
        super(Message.EXCEPTION + message);
    }

    public MyException(String message, Throwable cause) {
        super(Message.EXCEPTION + message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

    protected MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(Message.EXCEPTION + message, cause, enableSuppression, writableStackTrace);
    }
}
