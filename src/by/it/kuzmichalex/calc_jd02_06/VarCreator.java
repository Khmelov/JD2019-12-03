package by.it.kuzmichalex.calc_jd02_06;

/**
 * Всё стало ужасно. Но надо так надо! )))
 * */
abstract class VarCreator {
    abstract Var varFromExpression(String expression);

    abstract Var varFromExpression(double expression);

    abstract Var varFromExpression(double[] expression);

    abstract Var varFromExpression(double[][] expression);

}

class VarCreatorScalar extends VarCreator {
    @Override
    Var varFromExpression(String expression) { return new Scalar(expression); }

    @Override
    Var varFromExpression(double expression) { return new Scalar(expression); }

    @Override
    Var varFromExpression(double[] expression) { return new Vector(expression); }

    @Override
    Var varFromExpression(double[][] expression) { return new Matrix(expression); }
}

class VarCreatorVector extends VarCreator {
    @Override
    Var varFromExpression(String expression) { return new Vector(expression); }

    @Override
    Var varFromExpression(double expression) { return new Scalar(expression); }

    @Override
    Var varFromExpression(double[] expression) { return new Vector(expression); }

    @Override
    Var varFromExpression(double[][] expression) { return new Matrix(expression); }
}

class VarCreatorMatrix extends VarCreator {
    @Override
    Var varFromExpression(String expression) { return new Matrix(expression); }

    @Override
    Var varFromExpression(double expression) { return new Scalar(expression); }

    @Override
    Var varFromExpression(double[] expression) { return new Vector(expression); }

    @Override
    Var varFromExpression(double[][] expression) { return new Matrix(expression); }
}

enum VarTypes {Scalar, Vector, Matrix}

class VarCreatorByType {
    static private final VarCreator scalarCreator = new VarCreatorScalar();
    static private final VarCreator vectorCreator = new VarCreatorVector();
    static private final VarCreator matrixCreator = new VarCreatorMatrix();

    static VarCreator getVarCreator(VarTypes type) {
        if (type == VarTypes.Scalar) return scalarCreator;
        else if (type == VarTypes.Vector) return vectorCreator;
        return matrixCreator;
    }

    static VarCreator getVarCreator(double value){ return scalarCreator; }
    static VarCreator getVarCreator(double[] value){ return vectorCreator; }
    static VarCreator getVarCreator(double[][] value){ return matrixCreator; }
}


