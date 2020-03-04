package by.it.drozd.jd01_07;



class Matrix extends Var{
    private double[][] value;

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
