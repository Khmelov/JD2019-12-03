package by.it.shpakovskiy.calc02_05;

public interface ScalarOperations {
    Var add(Scalar scalar) throws CalcException;
    Var sub(Scalar scalar) throws CalcException;
    Var mul(Scalar scalar) throws CalcException;
    Var div(Scalar scalar) throws CalcException;
}
