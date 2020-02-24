package by.it.filipovich.jd02_02;

import java.util.Random;

class Helper {
    static final Random generator = new Random(System.nanoTime());

    static int random(int start, int finish){
        int rand = start+generator.nextInt(finish-start+1);
        return rand;
    }

    static int random(int finish){
        int rand = generator.nextInt(finish+1);
        return rand;
    }

    static void sleep(int timeout){
        try {
            Thread.sleep(timeout/ Dispatcher.K_SPEED);
        } catch (InterruptedException e) {
            System.out.println("Oooh,NO!!!");
        }
    }

}
