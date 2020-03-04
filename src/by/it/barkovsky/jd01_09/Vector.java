package by.it.barkovsky.jd01_09;

import java.util.Arrays;

class Vector extends Var {
    private double[] value;

    public double[] getValue() {
        return value;
    }


    Vector(double[] value){
        this.value=new double[value.length];
        System.arraycopy(value,0,this.value,0,value.length);
    }

    Vector(Vector vec) {
        this.value = new double[vec.value.length];
        System.arraycopy(vec.value, 0, this.value, 0, vec.value.length);
    }

    Vector(String s) {
        s = s.replace(".0", "");
        s = s.replace("{", "");
        s = s.replace("}", "");
        String[] split = s.split(",");
        this.value = new double[split.length];
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
            this.value[i] = Double.parseDouble(split[i]);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        StringBuilder des = new StringBuilder(", ");
        sb.append(this.value[0]);
        for (int i = 1; i < this.value.length; i++) {
            sb.append(des).append(this.value[i]);
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public Var add(Var other) {
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < this.value.length; i++) {
                res[i] = ((Scalar)other).getValue() + res[i];
            }
            return new Vector(res);
        }
        else if (other instanceof Vector) {
            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < this.value.length; i++) {
                res[i] = res[i] + ((Vector)other).value[i];
            }
            return new Vector(res);
        } else
            return super.add(other);
    }

    @Override
    public Var sub(Var other) {
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(this.value, this.value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] -= ((Scalar) other).getValue();
            }
            return new Vector(res);
        } else if (other instanceof Vector)
            if (this.value.length == ((Vector) other).value.length) {
                Scalar min = new Scalar(-1);
                return this.add(min.mul(other));
            }
        return super.sub(other);
    }

    @Override
    public Var mul(Var other) {
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(this.value, this.value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] *= ((Scalar) other).getValue();
            }
            return new Vector(res);
        } else if (other instanceof Vector)
            if (this.value.length == ((Vector) other).value.length){
                double res = 0;
                for (int i = 0; i < this.value.length; i++) {
                    res+=this.value[i]*((Vector)other).value[i];
                }
                return new Scalar(res);
            }
        return super.mul(other);
    }

    @Override
    public Var div(Var other) {
        if (other instanceof Scalar){
            double[] res = Arrays.copyOf(this.value, this.value.length);
            for (int j = 0; j < res.length; j++) {
                res[j]/=((Scalar) other).getValue();
            }
            return new Vector(res);
        }
        return super.div(other);
    }

}
