package by.it.borodachev.Calc;

import java.util.Scanner;

public class ConsoleRunner {

    public static void main(String[] args) throws Exception {
        Var.loadvar();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        LogClass.getInstance().save2log(LanguageManager.get("message.calculator_running"));
        Printer printer = new Printer();
        while (true){
            String expression = scanner.next();
            if (expression.equals("end"))
            {
                Var.savevar();
                LogClass.getInstance().save2log("");
                break;
            }
            try {
                Var result = parser.calc(expression);
//                if  (!(result==null))
//                printer.print(result);
            }
         catch (CalcException e) {
             LogClass.getInstance().save2log("",e);
        }
        }
    }
}
