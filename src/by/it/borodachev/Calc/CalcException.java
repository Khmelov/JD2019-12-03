package by.it.borodachev.Calc;

public class CalcException extends Exception {
    public CalcException(String message) {
        super("ERROR: "+message);
        LogClass.getInstance().save2log(message,this);
     }
    public CalcException() {
        super();
        LogClass.getInstance().save2log("Error",this);
    }
    public CalcException(String message, Throwable cause) {
        super("ERROR: "+message, cause);
        LogClass.getInstance().save2log(message, (Exception) cause);
    }
    public CalcException(Throwable cause)
    {
        super(cause);
        LogClass.getInstance().save2log("Error",(Exception)cause);

    }
}
