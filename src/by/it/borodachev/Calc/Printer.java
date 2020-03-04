package by.it.borodachev.Calc;

import static by.it.borodachev.Calc.LogClass.*;

class Printer {

    void print(Var var) {
        if (var != null) {
            getInstance().save2log(var.toString());
           }
        else {
            String infoTxt =LanguageManager.get(ErrorMessage.unknown_error);
            getInstance().save2log(infoTxt);
        }
    }

}
