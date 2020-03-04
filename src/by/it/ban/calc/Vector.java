package by.it.ban.calc;

import java.util.Arrays;

class Vector extends Var {
    private double[] value;

    Vector(double[] value) {
        this.value = new double[value.length];
        System.arraycopy(value, 0, this.value, 0, value.length);

    }

    Vector(Vector vector) {
        this.value = new double[vector.value.length];
        System.arraycopy(vector.value, 0, this.value, 0, vector.value.length);
    }

    Vector(String strVector) {
        String[] vectorStr = strVector.trim().substring(1, strVector.length() - 1).split(",");
        this.value = new double[vectorStr.length];
        for (int i = 0; i < vectorStr.length; i++) {
            this.value[i] = Double.parseDouble(vectorStr[i]);
        }


    }

    public double[] getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("{");
        String delimiter = "";
        for (double element : value) {
            res.append(delimiter).append(element);
            delimiter = ", ";
        }
        res.append("}");
        return res.toString();
    }

    @Override
    public Var add(Var other) throws CalcException {
        return other.add(this);
    }

    @Override
    public Var add(Scalar other) throws CalcException {
        double[] op1 = Arrays.copyOf(value, value.length);
        for (int i = 0; i < op1.length; i++) {
            op1[i] += other.getValue();
        }
        return new Vector(op1);
    }

    @Override
    public Var add(Vector other) throws CalcException {
        if (this.value.length == other.value.length) {
            double[] op1 = Arrays.copyOf(value, value.length);
            for (int i = 0; i < op1.length; i++) {
                op1[i] += other.value[i];
            }
            return new Vector(op1);
        }
        return super.mul(other);
    }

    @Override
    public Var sub(Var other) throws CalcException {
        return other.sub(this);
    }

    @Override
    public Var sub(Scalar other) throws CalcException {
        double[] op1 = Arrays.copyOf(value, value.length);
        for (int i = 0; i < op1.length; i++) {
            op1[i] = other.getValue() - op1[i];
        }
        return new Vector(op1);
    }

    @Override
    public Var sub(Vector other) throws CalcException {
        if (this.value.length == other.value.length) {
            double[] op1 = Arrays.copyOf(other.value, other.value.length);
            for (int i = 0; i < op1.length; i++) {
                op1[i] -= value[i];
            }
            return new Vector(op1);
        }
        return super.sub(other);
    }

    @Override
    public Var mul(Var other) throws CalcException {
        return other.mul(this);
    }

    @Override
    public Var mul(Scalar other) throws CalcException {
        double[] op1 = Arrays.copyOf(value, value.length);
        for (int i = 0; i < op1.length; i++) {
            op1[i] *= other.getValue();
        }
        return new Vector(op1);
    }

    @Override
    public Var mul(Vector other) throws CalcException {
        if (this.value.length == other.value.length) {
            double res = 0;
            for (int i = 0; i < value.length; i++) {
                res += value[i] * other.value[i];
            }
            return new Scalar(res);
        }
        return super.mul(other);
    }

    @Override
    public Var mul(Matrix other) throws CalcException {
        if (other.getValue()[0].length == value.length) {
            double[] op1 = new double[value.length];
            for (int i = 0; i < op1.length; i++) {
                for (int j = 0; j < other.getValue()[i].length; j++) {
                    op1[i] = op1[i] + value[j] * other.getValue()[i][j];
                }
            }
            return new Vector(op1);
        }
        return super.mul(other);
    }

    @Override
    public Var div(Var other) throws CalcException {
        return other.div(this);
    }


}
