package by.it.lozouski.jd02_06;

class Runner {
    public static void main(String[] args) {
        Logger singleTonLogger = Logger.getInstance();
        int i = 2;
        int j = 2;
        singleTonLogger.addLogMessage("int i = " + i);
        singleTonLogger.addLogMessage("int j = " + j);
        int sum = i + j;
        singleTonLogger.addLogMessage(Integer.toString(sum));
        singleTonLogger.addLogMessage("Test exp: " + i + " + " + j + " = " + sum);
        System.out.println("Test expression: " + i + " + " + j + " = " + sum);

        ///log error test
        try {
            String testError = "test";
            int x = Integer.parseInt(testError);
            System.out.println(x);
        }catch (NumberFormatException e){
            singleTonLogger.addLogMessage("ERROR Number format Exception!");
            System.err.println("ERROR Number format Exception!");
        }
    }
}
