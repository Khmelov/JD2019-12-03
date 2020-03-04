package by.it.busel.calc02_06;

/**
 * a factory to create a Scalar
 */
public class ScalarCreator extends VarCreator {
    /**
     * a method that creates Scalar on the basis of a method argument
     *
     * @param expression a String that contains a Scalar expression
     * @return a Scalar object
     */
    @Override
    public Var create(String expression) {
        return new Scalar(expression);
    }
}