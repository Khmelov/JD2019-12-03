package by.it.busel.calc02_06;

/**
 * a factory to create a Matrix
 */
public class MatrixCreator extends VarCreator {
    /**
     * a method that creates Matrix on the basis of a method argument
     *
     * @return a Matrix object
     */
    @Override
    public Var create(String expression) {
        return new Matrix(expression);
    }
}