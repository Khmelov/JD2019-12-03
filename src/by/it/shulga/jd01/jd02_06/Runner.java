package by.it.shulga.jd01.jd02_06;

public class Runner {
    public static void main(String[] args) {
        divideNumbers(0,6);
    }

    private static void divideNumbers(double first, double second){
        double result = first/second;
        if(result!=0)
            System.out.println(result);
        else {
            Logger.getInstance().logWrite("Division by zero");
        }
    }
}
