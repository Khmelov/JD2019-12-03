package by.it.plugatar.calc;

import java.io.IOException;

public class Printer  {
    void print(Var var) throws CalcException, IOException {
        if (var != null) {
            System.out.println(var);
            LogFile.print(var.toString());
        }
        else
            //System.out.println("Unknown ERROR!!!");
        throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.UNKNOWN));
    }
}
