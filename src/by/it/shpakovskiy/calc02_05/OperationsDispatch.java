package by.it.shpakovskiy.calc02_05;

public interface OperationsDispatch {
    Var addDispatch(Var other) throws CalcException;
    Var subDispatch(Var other) throws CalcException;
    Var mulDispatch(Var other) throws CalcException;
    Var divDispatch(Var other) throws CalcException;
}
