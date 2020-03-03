package by.it.tarasevich.calc;


class Scalar extends Var {
    private double value;

    public double getValue() {
        return value;
    }

    Scalar(double value) {
        this.value = value;
    }

    Scalar(String str) {
        this.value = Double.parseDouble(str);
    }

    Scalar(Scalar scalar) {
        this.value = scalar.value;
    }

    @Override
    public Var add(Var other) throws CalcException {
        if (other instanceof Scalar) {
            double sum = this.value + ((Scalar) other).value;
            return new Scalar(sum);
        } else
            return other.add(this);
    }

    @Override
    public Var sub(Var other) throws CalcException {
        if (other instanceof Scalar) {
            double sub = this.value - ((Scalar) other).value;
            return new Scalar(sub);
        } else
            return new Scalar(-1).mul(other).add(this);
    }

    @Override
    public Var mul(Var other) throws CalcException{
        if (other instanceof Scalar) {
            double mul = this.value * ((Scalar) other).value;
            return new Scalar(mul);
        }
        else
        return other.mul(this);
    }

    @Override
    public Var div(Var other) throws CalcException{
                   if (other instanceof Scalar) {
                double op2=((Scalar) other).value;
                if (op2==0){
                    CalcException exception = new CalcException("Omg!! Division by zero");
                    throw exception;
                }
                return new Scalar(this.value / ((Scalar) other).value);
            }
            return super.div(other);
        }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
