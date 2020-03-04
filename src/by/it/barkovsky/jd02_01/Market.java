package by.it.barkovsky.jd02_01;

import java.util.ArrayList;
import java.util.List;

class Market{

    public static void main(String[] args) {
        System.out.println("OPEN");
        List<Buyer> buyers = new ArrayList<>(1000);
        int number=0;

        for(int time = 0; time <= 120; time++){
            int count = Helper.random(0,2);
            for (int i = 0; i < count; i++) {
                Buyer buyer = new Buyer(++number);
                buyers.add(buyer);
                buyer.start();
            }
            Helper.sleep(1000);
        }

        for (Buyer buyer : buyers) {
            try {
                buyer.join();
            } catch (InterruptedException e) {
                System.out.println("error");
            }
        }
        System.out.println("CLOSE");
    }
}
