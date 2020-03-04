package by.it.busel.calc02_06;

/**
 * an abstract class-factory to create Var object
 */
abstract class VarCreator {
    /**
     * an abstract method that creates a Var expression on the basis of a method argument
     *
     * @param expression a String that contains Var's subclasses
     * @return a Var's subclass object
     */
    public abstract Var create(String expression);
}