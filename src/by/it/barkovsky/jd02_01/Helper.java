package by.it.barkovsky.jd02_01;

import java.util.Random;

class Helper {
    private static final Random generator = new Random(System.nanoTime());

    static int random(int start, int finish){
        return start+generator.nextInt(finish-start+1);
    }

    static void sleep(int timeout){
        try {
            Thread.sleep(timeout/ Dispatcher.K_SPEED);
        } catch (InterruptedException e) {
            System.out.println("Oooh,NO!!!");
        }
    }

}
