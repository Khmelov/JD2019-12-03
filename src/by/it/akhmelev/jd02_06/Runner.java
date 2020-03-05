package by.it.akhmelev.jd02_06;

public class Runner {

    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.save("Hi");
        System.out.println(logger);

        logger = Logger.getInstance();
        logger.save("Ok");
        System.out.println(logger);

        for (int i = 0; i < 100; i++) {
            new Thread(() -> Logger.getInstance().save("message")).run();
        }

    }

}
