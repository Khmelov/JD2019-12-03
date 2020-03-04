package by.it.drozd.jd01_08;

import java.util.Arrays;

class Matrix extends Var {
    private double[][] value;

    @Override
    public Var add(Var other) {
        if(other instanceof Matrix){
            double[][] array= Arrays.copyOf(value,value.length);
            double[][] newArray = new double[array.length][value.length];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < value[0].length; j++) {
                    for (int k = 0; k < value.length; k++) {
                        newArray[i][j]+=array[i][k]*value[k][j];
                    }
                }
            }
            return new Matrix(newArray);
        }else if(other instanceof Vector){
        double[] vector=Arrays.copyOf(((Vector) other).getValue(),((Vector) other).getValue().length);
        double[] newVector=new double[vector.length];
            for (int i = 0; i < value.length; i++) {
                for (int j = 0; j < vector.length; j++) {
                    newVector[i]+=value[i][j]*vector[j];
                }
            }
            return new Vector((newVector));
        }else{
            return super.mul(other);
        }
    }

    @Override
    public Var sub(Var other) {
        return super.sub(other);
    }

    @Override
    public Var mul(Var other) {
        if(other instanceof Matrix){
            double[][] array= Arrays.copyOf(value,value.length);
            double[][] newArray = new double[array.length][value.length];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < value[0].length; j++) {
                    for (int k = 0; k < value.length; k++) {
                        newArray[i][j]+=array[i][k]*value[k][j];
                    }
                }
            }
            return new Matrix(newArray);
        }else if(other instanceof Vector){
            double[] vector=Arrays.copyOf(((Vector) other).getValue(),((Vector) other).getValue().length);
            double[] newVector=new double[vector.length];
            for (int i = 0; i < value.length; i++) {
                for (int j = 0; j < vector.length; j++) {
                    newVector[i]+=value[i][j]*vector[j];
                }
            }
            return new Vector((newVector));
        }else{
            return super.mul(other);
        }
    }

    Matrix(double[][] value){
        this.value=value;
    }
    Matrix(Matrix matrix){
        this.value=matrix.value;
    }
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("{");
        String delimiter="";
        String comma="";
        for (int i = 0; i < value.length; i++) {
            sb.append(comma);
            sb.append("{");
            delimiter="";
            for (int i1 = 0; i1 < value[0].length; i1++) {
                sb.append(delimiter).append(value[i][i1]);
                delimiter=", ";
            }

            sb.append("}");
            comma=",";
        }


        sb.append("}");
        return sb.toString();
    }
}
