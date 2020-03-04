package by.it.ban.calc;

public class VarFactory {
    public Var createVar(String strVar) throws CalcException {
        if (strVar.matches(Patterns.SCALAR))
            return new Scalar(strVar);

        else if (strVar.matches(Patterns.VECTOR))
            return new Vector(strVar);

        else if (strVar.matches(Patterns.MATRIX))
            return new Matrix(strVar);
        else if (Var.getVars().containsKey(strVar))
            return Var.getVars().get(strVar);
        else
            throw new CalcException(ResManager.get(ErrorMessages.INIT));
    }

}
