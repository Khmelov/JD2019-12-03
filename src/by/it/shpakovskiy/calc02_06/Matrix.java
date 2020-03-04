package by.it.shpakovskiy.calc02_06;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Matrix extends Var {
    private double[][] value;

    Matrix(double[][] value) throws CalcException {
        if (value.length == 0) {
            this.value = new double[0][];
        } else {
            int cols = value[0].length;
            for (double[] aValue : value) { // Check: matrix should be rectangle
                if (aValue.length != cols) {
                    throw new CalcException(ResMan.get("wrongArrSize"));
                }
            }
            this.value = new double[value.length][cols];
            for (int i = 0; i < value.length; i++) {
                System.arraycopy(value[i], 0, this.value[i], 0, value[i].length);
            }
        }
    }

    @SuppressWarnings("unused")
    Matrix(Matrix matrix) throws CalcException {
        this(matrix.value);
    }

    Matrix(String strMatrix) throws CalcException {
        String strWithoutWhiteSpaces = strMatrix.replaceAll("\\s", "");
        if (!strWithoutWhiteSpaces.matches("[{]([{][^{}]+[}],)*[{][^{}]+[}][}]")) { // Pattern to string like {{2.0,3,9},{4.75,6,0},{1e2,0xA,010}}
            throw new CalcException(ResMan.get("matrixNotTemplate"));
        }
        Pattern extCurlyBrackets = Pattern.compile("[{](.*)[}]"); // Find all inside external curly brackets
        Matcher m1 = extCurlyBrackets.matcher(strWithoutWhiteSpaces);
        if (m1.find()) {
            Pattern intCurlyBrackets = Pattern.compile("[{]([^}]*)[}]"); // Find all internal curly brackets
            Matcher m2 = intCurlyBrackets.matcher(m1.group(1));
            int counter = 0;
            while (m2.find()) counter++; // First step - simple counting internal {}
            if (counter == 0) {
                throw new CalcException(ResMan.get("emptyMatrix"));
            }
            value = new double[counter][];
            m2.reset();
            int i = 0;
            while (m2.find()) {
                String[] strRows = m2.group(1).split(",");
                value[i] = new double[strRows.length];
                for (int j = 0; j < strRows.length; j++) {
                    try {
                        value[i][j] = Double.parseDouble(strRows[j]);
                    } catch (NumberFormatException e) {
                        throw new CalcException(ResMan.get("wrongValue") + " " + strRows[j] + ". " + e.getMessage());
                    }
                }
                i++;
            }
            // Now check is our matrix a rectangle?
            int len = value[0].length; // At this point value has at least one element
            for (double[] v : value) {
                if (v.length != len) {
                    throw new CalcException(ResMan.get("matrixDoesntRectangle"));
                }
            }
        } else {
            throw new CalcException(ResMan.get("matrixNotTemplate"));
        }
    }

    public double[][] getValue() {
        return value;
    }

    public void setValue(double[][] value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{{");
        String s = "";
        for (double[] aValue : value) {
            for (double v : aValue) {
                stringBuilder.append(s).append(v);
                s = ", ";
            }
            stringBuilder.append("}");
            s = ", {";
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    // Matrix +- Scalar = Matrix (true - add; false - subtract)
    private Var addOrSub(Scalar other, boolean operation) throws CalcException {
        int op = operation ? 1 : -1;
        double s = other.getValue();
        double[][] nm = new double[value.length][];
        for (int i = 0; i < nm.length; i++) {
            nm[i] = new double[value[i].length];
            for (int j = 0; j < nm[i].length; j++) {
                nm[i][j] = value[i][j] + op * s;
            }
        }
        return new Matrix(nm);
    }

    // Matrix + Scalar = Matrix
    @Override
    public Var add(Scalar other) throws CalcException {
        return addOrSub(other, true);
    }

    // Matrix - Scalar = Matrix
    @Override
    public Var sub(Scalar other) throws CalcException {
        return addOrSub(other, false);
    }

    // Matrix + Vector = null
    @Override
    public Var add(Vector other) throws CalcException {
        return super.add((Var) other);
    }

    // Matrix - Vector = null
    @Override
    public Var sub(Vector other) throws CalcException {
        return super.sub((Var) other);
    }

    // Matrix +- Matrix = Matrix or null (true - add; false - subtract)
    private Var addOrSub(Matrix other, boolean operation) throws CalcException {
        int op = operation ? 1 : -1;
        double[][] nm = new double[value.length][];
        if (other.value.length != value.length) {
            return operation ? super.add((Var) other) : super.sub((Var) other);
        }
        for (int i = 0; i < value.length; i++) {
            if (other.value[i].length != value[i].length) {
                return operation ? super.add((Var) other) : super.sub((Var) other);
            }
            nm[i] = new double[value[i].length];
            for (int j = 0; j < value[i].length; j++) {
                nm[i][j] = value[i][j] + op * other.value[i][j];
            }
        }
        return new Matrix(nm);
    }

    // Matrix + Matrix = Matrix or null
    @Override
    public Var add(Matrix other) throws CalcException {
        return addOrSub(other, true);
    }

    // Matrix - Matrix = Matrix or null
    @Override
    public Var sub(Matrix other) throws CalcException {
        return addOrSub(other, false);
    }

    // Matrix (*/) Scalar = Matrix (true - multiply; false - division)
    private Var mulOrDiv(Scalar other, boolean operation) throws CalcException {
        double s = other.getValue();
        double[][] a = new double[value.length][];
        for (int i = 0; i < a.length; i++) {
            a[i] = new double[value[i].length];
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = operation ? value[i][j] * s : value[i][j] / s;
            }
        }
        return new Matrix(a);
    }

    // Matrix * Scalar = Matrix
    @Override
    public Var mul(Scalar other) throws CalcException {
        return mulOrDiv(other, true);
    }

    // Matrix / Scalar = Matrix
    @Override
    public Var div(Scalar other) throws CalcException {
        return mulOrDiv(other, false);
    }

    // Matrix * Vector = Vector or null
    @Override
    public Var mul(Vector other) throws CalcException {
        int vLen = other.getValue().length; // "other" length
        int nvLen = value.length; // new vector length
        double[] otherVec = other.getValue();
        double[] nv = new double[nvLen];
        for (int i = 0; i < nvLen; i++) {
            if (value[i].length != vLen) return super.mul((Var) other);
            nv[i] = 0; // Not necessary, but just in case
            for (int j = 0; j < vLen; j++) {
                nv[i] += value[i][j] * otherVec[j];
            }
        }
        return new Vector(nv);
    }

    // Matrix / Vector = null
    @Override
    public Var div(Vector other) throws CalcException {
        return super.div((Var) other);
    }

    // Matrix * Matrix = Matrix or null
    @Override
    public Var mul(Matrix other) throws CalcException {
        int rows1 = value.length;
        if (rows1 == 0) return super.mul((Var) other);
        int cols1 = value[0].length;
        int rows2 = other.value.length;
        if (rows2 == 0) return super.mul((Var) other);
        int cols2 = other.value[0].length;
        if (cols1 != rows2) return super.mul((Var) other);
        double[][] nm = new double[rows1][cols2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    nm[i][j] += value[i][k] * other.value[k][j];
                }
            }
        }
        return new Matrix(nm);
    }

    // Matrix / Matrix = null
    @Override
    public Var div(Matrix other) throws CalcException {
        return super.div((Var) other);
    }

    @Override
    public Var add(Var other) throws CalcException {
        return other.addDispatch(this);
    }

    @Override
    public Var sub(Var other) throws CalcException {
        return other.subDispatch(this);
    }

    @Override
    public Var mul(Var other) throws CalcException {
        return other.mulDispatch(this);
    }

    @Override
    public Var div(Var other) throws CalcException {
        return other.divDispatch(this);
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