package by.it.ban.calc;

public class CalcException extends Exception{

    public CalcException() {
        super();
    }

    public CalcException(String message) {
        super(ResManager.get(ErrorMessages.ERR)+" "+message);
    }

    public CalcException(String message, Throwable cause) {
        super(ResManager.get(ErrorMessages.ERR)+" "+message, cause);
    }

    public CalcException(Throwable cause) {
        super(cause);
    }
}
