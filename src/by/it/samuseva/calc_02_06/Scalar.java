package by.it.samuseva.calc_02_06;

class Scalar extends Var {
    private double value;

    Scalar(double value) {
        this.value = value;
    }
    Scalar(Scalar scalar) {
        this.value = scalar.value;
    }
    Scalar(String str) {
        this.value = Double.parseDouble(str);
    }

//////////   methods for add operation
    @Override
    public Var add(Var right) throws CalcException {
       return right.add(this);
    }
    @Override
    public Var add(Scalar left) {
        double sum = this.value + ((Scalar) left).value;
        return new Scalar(sum);
    }
    @Override
    public Var add(Vector left) throws CalcException {
        return left.add(this);
    }
    @Override
    public Var add(Matrix left) throws CalcException {
        return left.add(this);
    }

//////////   methods for sub operation
    @Override
    public Var sub(Var rigth) throws CalcException {
        return rigth.sub(this);
    }
    @Override
    public Var sub(Scalar left) throws CalcException {
        return  new Scalar(-1*(this.value-left.value));
    }
    @Override
    public Var sub(Vector left) throws CalcException {
        return left.sub(this);
    }
    @Override
    public Var sub(Matrix left) throws CalcException {
        return left.sub(this);
    }

    //////////   methods for mul operation
    @Override
    public Var mul(Var rigth) throws CalcException {
         return rigth.mul(this);
    }
    @Override
    public Var mul(Scalar left) throws CalcException {
        return new Scalar(this.value *left.value);
    }
    @Override
    public Var mul(Vector left) throws CalcException {
        return left.mul(this);
    }
    @Override
    public Var mul(Matrix left) throws CalcException {
        return left.mul(this);
    }

    //////////   methods for div operation
    @Override
    public Var div(Var rigth) throws CalcException {
        return rigth.div(this);
    }
    @Override
    public Var div(Scalar left) throws CalcException {
        if (this.value==0) throw new CalcException("Делить на ноль нельзя!");
        return new Scalar(left.value/this.value);
    }

    @Override
    public Var div(Vector left) throws CalcException {
        if (this.value==0) throw new CalcException("Делить на ноль нельзя!");
        return left.mul(new Scalar(1.0/this.value));
    }

    @Override
    public Var div(Matrix left) throws CalcException {
        if (this.value==0) throw new CalcException("Делить на ноль нельзя!");
        return left.mul(new Scalar(1.0/this.value));
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
