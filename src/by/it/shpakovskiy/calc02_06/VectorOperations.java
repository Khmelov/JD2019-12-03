package by.it.shpakovskiy.calc02_06;

public interface VectorOperations {
    Var add(Vector vector) throws CalcException;
    Var sub(Vector vector) throws CalcException;
    Var mul(Vector vector) throws CalcException;
    Var div(Vector vector) throws CalcException;
}
