package by.it.shpakovskiy.calc02_05;

public class Patterns {
    static final String OPERATION = "(?<![-+*/={,]|,\\s|^)([-+=*/])";
    static final String SCALAR = "-?[0-9]+(.[0-9]+)?";
    static final String VECTOR = "[{]((" + SCALAR + ")(,?)(\\s?))+}";
    static final String MATRIX = "[{](" + VECTOR + ",?\\s?)+}";
    static final String PARENTHESES = "[(]([^()]+)[)]";

    private Patterns() {
    }
}
