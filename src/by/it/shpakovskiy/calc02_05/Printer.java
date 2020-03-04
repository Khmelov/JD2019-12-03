package by.it.shpakovskiy.calc02_05;

class Printer {
    void print(Var var) throws CalcException {
        if (var != null) System.out.println(var);
        else throw new CalcException(ResMan.get("wrongValue"));
    }
}
