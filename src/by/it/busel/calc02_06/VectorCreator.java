package by.it.busel.calc02_06;

/**
 * a factory to create a Matrix
 */
public class VectorCreator extends VarCreator {
    /**
     * a method that creates Vector on the basis of a method argument
     * @param expression a String that contains a Vector expression
     * @return a Vector object
     */
    @Override
    public Var create(String expression) {
        return new Vector(expression);
    }
}