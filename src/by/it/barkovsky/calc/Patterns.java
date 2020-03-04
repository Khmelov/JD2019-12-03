package by.it.barkovsky.calc;

public class Patterns {
    static final String OPERATION="[-+*/]";
    static final String SCALAR="-?[0-9]+(\\.[0-9]+)?";//"-?[0-9]+(.[0-9]+)?";//;//
    static final String VECTOR ="\\{(" + SCALAR + ",?\\s?)+}"; //"\\{"+SCALAR+"(,"+SCALAR+")*}";//;//;
    static final String MATRIX = "\\{(" + VECTOR + ",?\\s?)+}";//"\\{(" + VECTOR + ",?\\s?)+}";
}
