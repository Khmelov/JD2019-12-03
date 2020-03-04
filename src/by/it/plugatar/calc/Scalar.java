package by.it.plugatar.calc;

class Scalar extends Var {
    private double value;

    public double getValue() {
        return value;
    }

    Scalar(double value) {
        this.value = value;
    }

    @Override
    public Var add(Var other) throws CalcException{
        if (other instanceof Scalar){
            double sum=this.value+((Scalar) other).value;
            return new Scalar(sum);
            //Scalar op2 = (Scalar) other;
            //return new Scalar(value.this.value+op2.value);
        }
        else
            return other.add(this);
    }

    @Override
    public Var sub(Var other) throws CalcException{
        if (other instanceof Scalar){
            double sub=this.value-((Scalar) other).value;
            return new Scalar(sub);
        }
        else
           // return other.add(this);
            return new Scalar(-1).mul(other).add(this);
    }

    @Override
    public Var mul(Var other) throws CalcException{
        if (other instanceof Scalar){
            double mul=this.value*((Scalar) other).value;
            return new Scalar(mul);
            //Scalar op2 = (Scalar) other;
            //return new Scalar(value.this.value+op2.value);
        }
        else
            return other.mul(this);
    }

    @Override
    public Var div(Var other) throws CalcException{
        if (other instanceof Scalar){
            if (((Scalar) other).value==0)
                throw new CalcException(ConsoleRunner.lang.get(ErrorMessages.ZERO));
            double div=this.value/((Scalar) other).value;
            return new Scalar(div);
        }
        else
            return super.div(other);
    }

    Scalar(String str) {
        this.value = Double.parseDouble(str);
    }

    Scalar(Scalar scalar){
        this.value=scalar.value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
        //return "Это класс Scalar";
    }
/*
    Scalar (Scalar otherScalar)
*/

/*
    @Override
    public Var add(Var other){
        if (other instanceof Scalar){
            Scalar op2 = (Scalar) other;
            return new Scalar(value.this.value+op2.value);
        }
        return other.add(this);
    }
    */
/*
    @Override
    public Var sub(Var other){
        if (other instanceof Scalar){
            Scalar op2 = (Scalar) other;
            return new Scalar(value.this.value-((Scalar) other.value));
        }
        //return new Scalar(value:-1).mul(other.sub(this))//other.add(this);
    }
*/

}
