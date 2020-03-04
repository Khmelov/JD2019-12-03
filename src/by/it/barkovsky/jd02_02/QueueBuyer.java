package by.it.barkovsky.jd02_02;

import java.util.Deque;
import java.util.LinkedList;

public class QueueBuyer {
    private static Deque<Buyer> queue = new LinkedList<>();     //общая очередь покупателей
    private static Deque<Buyer> pensioneerQueue = new LinkedList<>(); // очередь для пенсионеров
    //размера очереди
    static int getQueueSize() {
        return queue.size();
    }
    //для добавления покупателя в очередь
    static synchronized void putToQueue(Buyer buyer) {
        if (buyer.pensioneer) pensioneerQueue.offerLast(buyer);
        else queue.offerLast(buyer);
        synchronized (Dispatcher.LOCK_QUEUE){
            Dispatcher.LOCK_QUEUE.notify();
        }
    }
    //для вывода покупателя из очереди (в кассу)
    static synchronized Buyer takeOutOfQueue() {
        if (pensioneerQueue.size() > 0) return pensioneerQueue.pollFirst();
        else if (queue.size() > 0) return queue.pollFirst();
        else return null;
    }


}