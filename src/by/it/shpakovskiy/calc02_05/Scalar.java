package by.it.shpakovskiy.calc02_05;

class Scalar extends Var {
    private double value;

    Scalar(double value) {
        this.value = value;
    }

    @SuppressWarnings("unused")
    Scalar(Scalar otherScalar) {
        this.value = otherScalar.value;
    }

    Scalar(String strScalar) {
        value = Double.parseDouble(strScalar);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public Var add(Var other) throws CalcException {
        return other.addDispatch(this);
    }

    @Override
    public Var add(Scalar other) {
        return new Scalar(this.value + other.value);
    }

    @Override
    public Var add(Vector other) {
        return other.add(this);
    }

    @Override
    public Var add(Matrix other) throws CalcException {
        return other.add(this);
    }

    @Override
    public Var sub(Var other) throws CalcException {
        return other.subDispatch(this);
    }

    @Override
    public Var sub(Scalar other) {
        return new Scalar(this.value - other.value);
    }

    @Override
    public Var sub(Vector other) throws CalcException {
        return other.sub(this).mul(new Scalar(-1));
    }

    @Override
    public Var sub(Matrix other) throws CalcException {
        return other.sub(this).mul(new Scalar(-1));
    }

    @Override
    public Var mul(Var other) throws CalcException {
        return other.mulDispatch(this);
    }

    @Override
    public Var mul(Scalar other) {
        return new Scalar(this.value * other.value);
    }

    @Override
    public Var mul(Vector other) {
        return other.mul(this);
    }

    @Override
    public Var mul(Matrix other) throws CalcException {
        return other.mul(this);
    }

    @Override
    public Var div(Var other) throws CalcException {
        return other.divDispatch(this);
    }

    @Override
    public Var div(Scalar other) throws CalcException {
        if (other.value == 0)
            throw new CalcException(ResMan.get("divisionByZero"));
        return new Scalar(value / other.value);
    }

    @Override
    public Var div(Vector other) throws CalcException {
        return super.div((Var) other);
    }

    @Override
    public Var div(Matrix other) throws CalcException {
        return super.div((Var) other);
    }

    @Override
    public Var addDispatch(Var other) throws CalcException {
        return other.add(this);
    }

    @Override
    public Var subDispatch(Var other) throws CalcException {
        return other.sub(this);
    }

    @Override
    public Var mulDispatch(Var other) throws CalcException {
        return other.mul(this);
    }

    @Override
    public Var divDispatch(Var other) throws CalcException {
        return other.div(this);
    }
}