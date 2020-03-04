package by.it.ban.calc;

import java.util.Arrays;

class Matrix extends Var {
    private double[][] value;

    Matrix(double[][] value) {
        this.value = new double[value.length][value[0].length];
        for (int i = 0; i < value.length; i++) {
            System.arraycopy(value[i], 0, this.value[i], 0, value[i].length);
        }
    }

    Matrix(Matrix matrix) {
        this.value = new double[matrix.value.length][matrix.value[0].length];
        for (int i = 0; i < matrix.value.length; i++) {
            System.arraycopy(matrix.value[i], 0, this.value[i], 0, matrix.value[i].length);
        }
    }

    Matrix(String strMatrix) {
        strMatrix = strMatrix.replaceAll("[{|}]{2,}", "");
        String[] stringValue = strMatrix.split("[}][\\s]?,[\\s]?[{]");
        value = new double[stringValue.length][];
        for (int i = 0; i < stringValue.length; i++) {
            String[] valueStringNumber = stringValue[i].trim().split(",");
            double[] tempArr = new double[valueStringNumber.length];
            for (int j = 0; j < valueStringNumber.length; j++) {
                tempArr[j] = Double.parseDouble(valueStringNumber[j]);
                value[i] = tempArr;
            }
        }
    }

    public double[][] getValue() {
        return value;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("{");
        for (int i = 0; i < value.length; i++) {
            res.append("{");
            String delimiter = "";
            for (double element : value[i]) {
                res.append(delimiter).append(element);
                delimiter = ", ";
            }
            res.append("}");
            if (i + 1 < value.length) res.append(", ");
        }
        res.append("}");
        return res.toString();
    }

    @Override
    public Var add(Var other) throws CalcException {
        return other.add(this);
    }

    @Override
    public Var sub(Var other) throws CalcException {
        return other.sub(this);
    }

    @Override
    public Var mul(Var other) throws CalcException {
        return other.mul(this);
    }

    @Override
    public Var div(Var other) throws CalcException {
        return other.div(this);
    }

    @Override
    public Var add(Scalar other) throws CalcException  {
        double[][] op1 = Arrays.copyOf(value, value.length);
        for (int i = 0; i < op1.length; i++) {
            op1[i] = Arrays.copyOf(value[i], value[i].length);
        }
        for (int i = 0; i < op1.length; i++) {
            for (int j = 0; j < op1[i].length; j++) {
                op1[i][j] = other.getValue() + op1[i][j];
            }

        }
        return new Matrix(op1);
    }

    @Override
    public Var sub(Scalar other) throws CalcException  {
        double[][] op1 = Arrays.copyOf(value, value.length);
        for (int i = 0; i < op1.length; i++) {
            op1[i] = Arrays.copyOf(value[i], value[i].length);
        }
        for (int i = 0; i < op1.length; i++) {
            for (int j = 0; j < op1[i].length; j++) {
                op1[i][j] = other.getValue() - op1[i][j];
            }

        }
        return new Matrix(op1);
    }

    @Override
    public Var mul(Scalar other) throws CalcException  {
        double[][] op1 = Arrays.copyOf(value, value.length);
        for (int i = 0; i < op1.length; i++) {
            op1[i] = Arrays.copyOf(value[i], value[i].length);
        }
        for (int i = 0; i < op1.length; i++) {
            for (int j = 0; j < op1[i].length; j++) {
                op1[i][j] = other.getValue() * op1[i][j];
            }

        }
        return new Matrix(op1);
    }


    @Override
    public Var add(Matrix other) throws CalcException  {
        if (value.length == other.value.length && value[0].length == other.value[0].length) {
            double[][] res = new double[value.length][value[0].length];
            for (int i = 0; i < value.length; i++) {
                for (int j = 0; j < value[i].length; j++) {
                    res[i][j] = value[i][j] + other.value[i][j];
                }
            }
            return new Matrix(res);
        }
        return super.add(other);

    }

    @Override
    public Var sub(Matrix other) throws CalcException  {
        if (value.length == other.value.length && value[0].length == other.value[0].length) {
            double[][] res = new double[value.length][value[0].length];
            for (int i = 0; i < value.length; i++) {
                for (int j = 0; j < value[i].length; j++) {
                    res[i][j] = other.value[i][j] - value[i][j];
                }
            }
            return new Matrix(res);
        }
        return super.sub(other);
    }

    @Override
    public Var mul(Matrix other) throws CalcException  {
        if (value.length == other.value[0].length) {
            double[][] res = new double[value.length][value[0].length];
            for (int i = 0; i < other.value.length; i++) {
                for (int j = 0; j < value[0].length; j++) {
                    for (int k = 0; k < value.length; k++) {
                        res[i][j] = res[i][j] + other.value[i][k] * value[k][j];
                    }

                }

            }
            return new Matrix(res);
        }
        return super.mul(other);
    }


}
