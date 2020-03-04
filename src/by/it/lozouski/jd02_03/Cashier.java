package by.it.lozouski.jd02_03;

import java.util.Arrays;


class Cashier implements Runnable {
    static final Object monitorCashier = new Object();

    static volatile int pieceCashiers = 5;

    private String name;

    public Cashier(int idCashier) {
        name = "КАССИР №" + idCashier + " ";
    }


    @Override
    public void run() {
        System.out.println(this + "ОТКРЫЛСЯ!");
        while (!Dispetcher.marketClosed()) {
            Buyer pensBuyer = QueueBuyer.extractPensBuyerFromQueue();
            if (pensBuyer != null) {
                serviceBuyer(pensBuyer);
            } else {
                Buyer buyer = QueueBuyer.extractBuyerFromQueue();
                if (buyer != null) {
                    serviceBuyer(buyer);
                } else {
                    synchronized (monitorCashier) {
                        try {
                            pieceCashiers--;
                            monitorCashier.wait();
                            pieceCashiers++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        System.out.println(this + "ЗАКРЫЛСЯ!");
    }

    void serviceBuyer(Buyer buyer) {
        StringBuilder sb = new StringBuilder();
        System.out.println(this + "НАЧАЛ ОБСЛУЖИВАНИЕ " + buyer);
        Help.sleep(Help.randomGenerate(2000, 5000));
        sb.append("Список товаров: ").append(buyer.toString()).append("--- ");
        sb.append(Arrays.toString(buyer.getBacketWithBoughtGoods().keySet().toArray())).append('\n');
        sb.append(this).append(buyer.toString()).append("--- Сумма чека = ").append(buyer.getAmountOfGoods()).append("руб.");
        System.out.println(sb);
        System.out.println(this + "ЗАКОНЧИЛ ОБСЛУЖИВАНИЕ " + buyer);
        synchronized (buyer) {
            buyer.setWaitFlag(false);
            buyer.notify();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
