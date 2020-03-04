package by.it.lozouski.jd02_03;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;

class QueueBuyer {
    private static BlockingDeque<Buyer> buyersQueue = new LinkedBlockingDeque<>(30);
    private static ConcurrentLinkedDeque<Buyer> pensQueue = new ConcurrentLinkedDeque<>();


    public static BlockingDeque<Buyer> getBuyersQueue() {
        return buyersQueue;
    }

    static void addBuyerToQueue(Buyer currBuyer) {
        buyersQueue.addLast(currBuyer);
    }

    static void addPensBuyerToQueue(Buyer currBuyer){
        pensQueue.addLast(currBuyer);
    }

    static Buyer extractBuyerFromQueue() {
        return buyersQueue.pollFirst();
    }
    static Buyer extractPensBuyerFromQueue(){
        return pensQueue.pollFirst();
    }
    static int buyerSizeQueue(){
        return buyersQueue.size();
    }
    static int pensBuyerSizeQueue(){
        return pensQueue.size();
    }
}
