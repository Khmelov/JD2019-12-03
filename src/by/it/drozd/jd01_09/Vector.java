package by.it.drozd.jd01_09;


import java.util.Arrays;

class Vector extends Var{
    private double[] value;

    public double[] getValue() {
        return value;
    }

    Vector(double[] value) {
        this.value = value;
    }
    Vector(Vector vector){
    this.value=vector.value;
    }
    Vector(String strVector){
        String nums = strVector.substring(1, strVector.length() - 1);
        this.value = Arrays.stream(nums.split(",")).mapToDouble(Double::parseDouble).toArray();
        }

    @Override
    public Var add(Var other) {
        if(other instanceof Vector){
            double[] array=Arrays.copyOf(value,value.length);
            for (int i = 0; i < this.value.length; i++) {
                array[i] += ((Vector) other).value[i];
            }
            return new Vector(array);
        }
        else if(other instanceof Scalar){
            double[] array=Arrays.copyOf(value,value.length);
            for (int i = 0; i < this.value.length; i++) {
                array[i] += ((Scalar) other).getValue();
            }
            return new Vector(array);
        }else{
            return super.add(other);
        }
    }

    @Override
    public Var sub(Var other) {
        if(other instanceof Vector){
            double[] array=Arrays.copyOf(value,value.length);
            for (int i = 0; i < this.value.length; i++) {
                array[i] -= ((Vector) other).value[i];
            }
            return new Vector(array);
        }
        else if(other instanceof Scalar){
            double[] array=Arrays.copyOf(value,value.length);
            for (int i = 0; i < this.value.length; i++) {
                array[i] -= ((Scalar) other).getValue();
            }
            return new Vector(array);
        }else{
            return super.sub(other);
        }
    }

    @Override
    public Var mul(Var other) {
        if (other instanceof Vector) {
            double[] array = Arrays.copyOf(value, value.length);
            double sum=0;
            for (int i = 0; i < this.value.length; i++) {
                array[i]*= ((Vector) other).value[i];
            }
            for (int i = 0; i < array.length; i++) {
                sum+=array[i];
            }
            return new Scalar(sum);
        } else if (other instanceof Scalar) {
            double[] array = Arrays.copyOf(value, value.length);
            for (int i = 0; i < this.value.length; i++) {
                array[i] *= ((Scalar) other).getValue();
            }
            return new Vector(array);
        } else {
            return super.mul(other);
        }
    }
    @Override
    public Var div(Var other) {
        if (other instanceof Vector) {
            return super.div(other);
        } else if (other instanceof Scalar) {
            double[] array = Arrays.copyOf(value, value.length);
            for (int i = 0; i < this.value.length; i++) {
                array[i] /= ((Scalar) other).getValue();
            }
            return new Vector(array);
        } else {
            return super.div(other);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder("{");
        String delimiter="";
        for (double element : value) {
            sb.append(delimiter).append(element);
            delimiter=", ";
        }
        sb.append("}");
        return sb.toString();
    }
}
