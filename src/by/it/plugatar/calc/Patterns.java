package by.it.plugatar.calc;

public class Patterns {
    static final String BRACKETS = "\\([-0-9+*/a-zA-Z.{},_ ]+\\)";
    static final String OPERATION="(?<=[^-+*/={,])[-+*/=]";//"[-=+*/]"
    static final String SCALAR="-?[0-9]+(\\.[0-9]+)?";//"-?[0-9]+(.[0-9]+)?";//;//
    static final String VECTOR="\\{"+SCALAR+"(,"+SCALAR+")*}"; //"\\{"+SCALAR+"(,"+SCALAR+")*}";//;//;
    static final String MATRIX="\\{"+VECTOR+"(,"+VECTOR+")*}";//"\\{(" + VECTOR + ",?\\s?)+}";

    private Patterns() {
    }
}
