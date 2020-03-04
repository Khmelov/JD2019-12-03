package by.it.shpakovskiy.calc02_06;

class Printer {
    void print(Var var) throws CalcException {
        if (var != null) System.out.println(var);
        else throw new CalcException(ResMan.get("wrongValue"));
    }
}
