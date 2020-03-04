package by.it.drozd.jd01_08;

class Runner {
    public static void main(String[] args) {
        Var s = new Scalar(3.0);
        Var v=new Vector(new double[]{1,2,3});
        //Var m = new Matrix("{{1,2,3},{4,5,6},{7,8,9}}");//
        System.out.println(s.add(s));
        System.out.println(s.sub(s));
        System.out.println(s.mul(s));
        System.out.println(s.div(s));

        System.out.println(v.add(v));
        System.out.println(v.sub(v));
    }


}
