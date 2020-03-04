package by.it.barkovsky.jd01_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matrix extends Var {
    private double[][] value;

    Matrix(double[][] array) {
        this.value = new double[array.length][array[0].length];
        for (int i = 0; i < array.length; i++)
            System.arraycopy(array[i], 0, this.value[i], 0, array[i].length);
    }

    Matrix(Matrix mat) {
        this.value = new double[mat.value.length][mat.value[0].length];
        for (int i = 0; i < mat.value.length; i++)
            System.arraycopy(mat.value[i], 0, this.value[i], 0, mat.value[i].length);
    }

    Matrix(String s) {
        //Убираем .0 и делимм строку на  массивы
        s = s.replace(".0", "");
        Pattern pt = Pattern.compile("[{][0-9., ]+[}]");
        Matcher m1 = pt.matcher(s);
        ArrayList<String> list = new ArrayList<>();
        while (m1.find()) list.add(m1.group());

        //выясняем кол-во членов в массивах и размер матрицы this
        String[] buf = list.get(0).split(",");
        this.value = new double[list.size()][buf.length];

        //убираем лишние символы из  массивов и переносим данные в матрицу this
        for (int i = 0; i < list.size(); i++) {
            String buff = list.get(i).replace("{","");
            buff = buff.replace("}","");
            String[] str = buff.split(",");
            for (int i1 = 0; i1 < str.length; i1++) {
                str[i1] = str[i1].trim();
            }
            for (int j = 0; j<str.length;j++){
                this.value[i][j] = Double.parseDouble(str[j]);
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        StringBuilder def = new StringBuilder(", ");
        for (int i = 0; i < this.value.length; i++) {
            sb.append("{").append(this.value[i][0]);
            for (int j = 1; j < this.value[i].length; j++)
                sb.append(def).append(this.value[i][j]);
            sb.append("}");
            if (i < this.value.length - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public Var add(Var other) {
        if (other instanceof Scalar) {
            double[][] res = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < res.length; i++) {
                res[i] = Arrays.copyOf(this.value[i], this.value[i].length);
            }
            for (int i = 0; i < res.length; i++)
                for (int j = 0; j < res[i].length; j++)
                    res[i][j] += ((Scalar) other).getValue();
            return new Matrix(res);
        } else if (other instanceof Matrix)
            if (this.value.length == ((Matrix) other).value.length &&
                    this.value[0].length == ((Matrix) other).value[0].length) {
                double[][] res = new double[this.value.length][this.value[0].length];
                for (int i = 0; i < res.length; i++) {
                    for (int j = 0; j < res[0].length; j++) {
                        res[i][j] = this.value[i][j] + ((Matrix) other).value[i][j];
                    }
                }
                return new Matrix(res);
            }

        return super.add(other);
    }

    @Override
    public Var sub(Var other) {
        if (other instanceof Scalar) {
            Scalar min = new Scalar(-1);
            return this.add(other.mul(min));
        } else if (other instanceof Matrix)
            if (this.value.length == ((Matrix) other).value.length &&
                    this.value[0].length == ((Matrix) other).value[0].length) {
                Scalar min = new Scalar(-1);
                return this.add(other.mul(min));
            }

        return super.add(other);
    }

    @Override
    public Var mul(Var other) {
        if (other instanceof Scalar) {
            double[][] res = new double[this.value.length][this.value[0].length];
            for (int i = 0; i < res.length; i++) {
                res[i] = Arrays.copyOf(this.value[i], this.value[i].length);
            }
            for (int i = 0; i < res.length; i++)
                for (int j = 0; j < res[i].length; j++)
                    res[i][j] *= ((Scalar) other).getValue();
            return new Matrix(res);
        } else if (other instanceof Matrix) {
            if (this.value[0].length == ((Matrix) other).value.length) {
                double[][] res = new double[this.value.length][((Matrix) other).value[0].length];
                for (int i = 0; i < res.length; i++)
                    for (int j = 0; j < res[i].length; j++)
                        for (int k = 0; k < this.value[0].length; k++)
                            res[i][j] += this.value[i][k] * ((Matrix) other).value[k][j];
                return new Matrix(res);
            }
        } else if (other instanceof Vector) {
            if (this.value[0].length == ((Vector) other).getValue().length){
                double[] res = new double[this.value.length];
                for (int i = 0; i < res.length; i++) {
                    for (int j = 0; j<this.value[0].length;j++){
                        res[i]+=this.value[i][j]*((Vector) other).getValue()[j];
                    }
                }
                return new Vector(res);
            }
        }
        return super.mul(other);
    }

}