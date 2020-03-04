package by.it.borodachev.jd01_08;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matrix extends Var {
      private double [][] value;
      private int rowLength;
      private int colLength;
      public int rows() { return rowLength;}
      public int cols() { return colLength;}
      public double getValue(int row,int col) throws MatrixException {
        if ((row<0)||(col<0)) throw new MatrixException("Неверный индекс");
        if ((row>=rowLength)||(col>=colLength)) throw new MatrixException("Неверный индекс");
        return this.value [row][col];
    }
    public Matrix(double [][] args) throws MatrixException {
        if (args.length == 0) throw new MatrixException("Длина массива =0");
        rowLength=args.length;
        colLength=args[0].length;
        value=new double [rowLength][colLength];
        for (int i = 0; (i < rowLength); i++) {
            for (int j = 0; (j < colLength); j++) {
                value[i][j] = args[i][j];
            }
        }
    }
    public Matrix(Matrix vc) throws MatrixException {
        if (vc.rows() == 0) throw new MatrixException("Длина массива =0");
        if (vc.cols() == 0) throw new MatrixException("Длина массива =0");

        rowLength=vc.rows();
        colLength=vc.cols();
        value=new double [rowLength][colLength];
        for (int i = 0; (i < rowLength); i++) {
            for (int j = 0; (j < colLength); j++) {
                value[i][j] = vc.getValue(i,j);
            }
        }
    }
    public Matrix(String str) throws MatrixException {
        int row=0;
        str=str.trim();
        str=str.replace(" ","");
        String[] valStr;
        Pattern pat= Pattern.compile("[^{}][0-9,/.]+");
 //       Pattern pat= Pattern.compile(MATRIX);
        Matcher m=pat.matcher(str);
        rowLength=0;
        while (m.find()) {rowLength++;};
        m.reset();
        while (m.find()) {
            valStr = m.group().split(",");
            if (row==0)  { colLength =valStr.length;
                value=new double [rowLength][colLength];
            }
            for (int j = 0; j < colLength; j++) {
                value[row][j]= new Double (valStr[j] );
            }
            row++;
        }
     }
     private StringBuilder rowToString (int row)  {
         String tmpStr;
      StringBuilder retStr =new StringBuilder("{");
         for (int col = 0; col < colLength - 1; col++) {
             tmpStr = Double.toString(value[row][col]);
             retStr.append(tmpStr.trim());
             retStr.append(", ");
         }
         tmpStr = Double.toString(value[row][colLength - 1]);
         retStr.append(tmpStr.trim());
         retStr.append("}");
         return retStr;
     }
    @Override
    public String toString() {

        StringBuilder retStr =new StringBuilder("{");
        for (int row = 0; row < rowLength-1; row++) {
            retStr.append(rowToString(row));
            retStr.append(", ");
        }
        retStr.append(rowToString(rowLength-1));
         retStr.append("}");
          return retStr.toString();
    }

    @Override
    public Var add(Var newValue) throws Exception {
        if (newValue instanceof Scalar) {
            Scalar tmpScalar = (Scalar) newValue;
            double[][] tmpArr =Arrays.copyOf(value, value.length);
            for (int i = 0; i < tmpArr.length; i++) {
                tmpArr[i] = Arrays.copyOf(value[i], value[i].length);
            }
            for (int i = 0; i < rowLength; i++) {
                for (int i1 = 0; i1 <colLength; i1++) {
                    tmpArr[i][i1] += tmpScalar.getValue() ;
                }
            }
            return new Matrix(tmpArr);
        }
        if (newValue instanceof Vector) {
            Vector tmpVector = (Vector) newValue;
            if (tmpVector.length() !=rowLength)  {throw new Exception("Diff Length"); }
            double[][] tmpArr =Arrays.copyOf(value, value.length);
            for (int i = 0; i < tmpArr.length; i++) {
                tmpArr[i] = Arrays.copyOf(value[i], value[i].length);
            }
            for (int i = 0; i < rowLength; i++) {
                for (int i1 = 0; i1 < colLength; i1++) {
                    tmpArr[i][i1] += tmpVector.getValue(i1) ;
                }
            }
               return new Matrix(tmpArr);

        }
        if (newValue instanceof Matrix) {
            Matrix tmpMatrix = (Matrix) newValue;
            if ((tmpMatrix.rows() !=rowLength) ||(tmpMatrix.cols()!=colLength))  {throw new Exception("Diff Length"); }
             double[][] tmpArr =Arrays.copyOf(value, value.length);
            for (int i = 0; i < tmpArr.length; i++) {
                tmpArr[i] = Arrays.copyOf(value[i], value[i].length);
            }
            for (int i = 0; i < rowLength; i++) {
                for (int i1 = 0; i1 < colLength; i1++) {
                    tmpArr[i][i1] += tmpMatrix.getValue(i,i1) ;
                }
            }
            return new Matrix(tmpArr);
        }

        return super.add(newValue);
    }

    @Override
    public Var sub(Var newValue) throws Exception {
        if (newValue instanceof Scalar) {
            Scalar tmpScalar = (Scalar) newValue;
            double[][] tmpArr =Arrays.copyOf(value, value.length);
            for (int i = 0; i < tmpArr.length; i++) {
                tmpArr[i] = Arrays.copyOf(value[i], value[i].length);
            }
            for (int i = 0; i < rowLength; i++) {
                for (int i1 = 0; i1 <colLength; i1++) {
                    tmpArr[i][i1] -= tmpScalar.getValue() ;
                }
            }
            return new Matrix(tmpArr);
        }
        if (newValue instanceof Vector) {
            Vector tmpVector = (Vector) newValue;
            if (tmpVector.length() !=rowLength)  {throw new Exception("Diff Length"); }
            double[][] tmpArr =Arrays.copyOf(value, value.length);
            for (int i = 0; i < tmpArr.length; i++) {
                tmpArr[i] = Arrays.copyOf(value[i], value[i].length);
            }
            for (int i = 0; i < rowLength; i++) {
                for (int i1 = 0; i1 < colLength; i1++) {
                    tmpArr[i][i1] -= tmpVector.getValue(i1) ;
                }
            }
            return new Matrix(tmpArr);

        }
        if (newValue instanceof Matrix) {
            Matrix tmpMatrix = (Matrix) newValue;
            if ((tmpMatrix.rows() !=rowLength) ||(tmpMatrix.cols()!=colLength))  {throw new Exception("Diff Length"); }
            double[][] tmpArr =Arrays.copyOf(value, value.length);
            for (int i = 0; i < tmpArr.length; i++) {
                tmpArr[i] = Arrays.copyOf(value[i], value[i].length);
            }
            for (int i = 0; i < rowLength; i++) {
                for (int i1 = 0; i1 < colLength; i1++) {
                    tmpArr[i][i1] -= tmpMatrix.getValue(i,i1) ;
                }
            }
            return new Matrix(tmpArr);
        }


        return super.sub(newValue);
    }

    @Override
    public Var mul(Var newValue) throws Exception {
        if (newValue instanceof Scalar) {
            Scalar tmpScalar = (Scalar) newValue;
            double[][] tmpArr =Arrays.copyOf(value, value.length);
            for (int i = 0; i < tmpArr.length; i++) {
                tmpArr[i] = Arrays.copyOf(value[i], value[i].length);
            }
            for (int i = 0; i < rowLength; i++) {
                for (int i1 = 0; i1 <colLength; i1++) {
                    tmpArr[i][i1] *= tmpScalar.getValue() ;
                }
            }
            return new Matrix(tmpArr);
        }
        if (newValue instanceof Vector) {
            Vector tmpVector = (Vector) newValue;
            if (tmpVector.length() !=rowLength)  {throw new Exception("Diff Length"); }
            double[] tmpArr =new double[rowLength];

            for (int i = 0; i < rowLength; i++) {
                for (int i1 = 0; i1 < colLength; i1++) {
                    tmpArr[i]+=value[i][i1] * tmpVector.getValue(i1) ;
                }
            }
            return new Vector(tmpArr);

        }
        if (newValue instanceof Matrix) {
            Matrix tmpMatrix = (Matrix) newValue;
            if ((tmpMatrix.rows() !=rowLength) ||(tmpMatrix.cols()!=colLength))  {throw new Exception("Diff Length"); }
            double[][] tmpArr =new double [rowLength][colLength];

            for (int i = 0; i < tmpMatrix.rows(); i++) {
                for (int j = 0; j < colLength; j++) {
                    for (int k = 0; k < rowLength; k++) {
                        tmpArr[i][j] += tmpMatrix.getValue(k,j) * value[i][k];
                    }

                }

            }
            return new Matrix(tmpArr);
        }

        return super.add(newValue);

    }

    @Override
    public Var div(Var newValue) throws Exception {
        return super.div(newValue);
    }
}
