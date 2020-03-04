package by.it.samuseva.jd02_06;

import java.text.DateFormat;
import java.util.Date;

public class Runner {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        String date = df.format(new Date());
        String message = "Massage ";
        //logger.save(message + 1);
        //logger.save(message + 2);
        for (int i = 1; i <10 ; i++) {
            logger.save(message + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
