package by.it.barkovsky.calc;

abstract class Var implements Operation {

    @Override
    public Var add(Var other) throws CalcException {
        throw new CalcException("Операция сложения " + this + "+" + other + " невозможна");
    }

    @Override
    public Var sub(Var other) throws CalcException {
        throw new CalcException("Операция вычитания " + this + "-" + other + " невозможна");
    }

    @Override
    public Var mul(Var other) throws CalcException {
        throw new CalcException("Операция умножения " + this + "*" + other + " невозможна");
    }

    @Override
    public Var div(Var other) throws CalcException {
        throw new CalcException("Операция деления " + this + "/" + other + " невозможна");
    }

    public Var() {

    }

    @Override
    public String toString() {
        return "this Var";
    }
    static Var createVar(String operand) throws CalcException {
        operand = operand.trim().replace("\\s+", "");
        if (operand.matches(Patterns.SCALAR)) {
            return new Scalar(operand);
        }
        if (operand.matches(Patterns.VECTOR)) {
            return new Vector(operand);
        }
        throw  new CalcException("Невозможно создать" + operand);
    }

}


