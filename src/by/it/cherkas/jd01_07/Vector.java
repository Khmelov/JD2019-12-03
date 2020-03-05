package by.it.cherkas.jd01_07;

        class Vector extends Var {

                private double[] value;


                 Vector(double[] value) {
                        this.value = new double[value.length];
                        System.arraycopy(value, 0, this.value,0, value.length);

                }
                Vector(Vector vector){
                         this.value=new double[vector.value.length];
                         System.arraycopy(vector.value, 0, this.value, 0, vector.value.length);
                }

                Vector(String strVector){
                         strVector = strVector.replaceAll("[{}]", "");
                         strVector = strVector.replaceAll(",", " ");
                         String[] doubNums = strVector.split(" ");
                         value = new double[doubNums.length];
                    for (int i = 0; i < doubNums.length; i++) {
                        value [i] = Double.parseDouble(doubNums[i]);

                        }
                }


                @Override
                public String toString() {
                StringBuilder sb=new StringBuilder("{");
                    for (int i = 0; i < value.length; i++) {
                        if (i !=value.length - 1) {
                            sb.append(value[i]).append(", ");
                        }
                        else {
                            sb.append(value[i]).append("}");
                        }
                    }
                    return sb.toString();
                }
        }




