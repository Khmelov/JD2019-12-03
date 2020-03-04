package by.it.barkovsky.jd02_02;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class Cashier implements Runnable {
    private String name;
    private int numberOfCashier;

    public Cashier(int number) {
        Dispatcher.newCashier();
        this.name = "Cashier №" + number;
        this.numberOfCashier = number;
    }

    @Override
    public void run() {
        synchronized (Dispatcher.LOCK_CONSOLE) {
            System.out.println(this + " opened");
        }
        while (!Dispatcher.planIsComplete() && Dispatcher.getBuyersCount() >=
                (Dispatcher.getCountCashiers() - 1) * 5) {
            cashiersWork();
        }
        if (Dispatcher.getCountCashiers() == 1){
            synchronized (Dispatcher.LOCK_ARRAY){
                Dispatcher.LOCK_ARRAY.notify();
            }
        }
        Dispatcher.cashierCloses();
        synchronized (Dispatcher.LOCK_CONSOLE) {
            System.out.println(this + " closed");
        }
    }

    //касса в рабочем состоянии
    private void cashiersWork() {
        startMoreCashiers();
        Buyer buyer = QueueBuyer.takeOutOfQueue();
        if (buyer != null) {
            cashierServiceBuyer(buyer);
            logCashier(buyer);
        } else if (Dispatcher.getCountCashiers() <= 1) {
            synchronized (Dispatcher.LOCK_QUEUE) {
                try {
                    Dispatcher.LOCK_QUEUE.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //касса обслуживает покупателя
    private void cashierServiceBuyer(Buyer buyer) {
        String s = getShiftForConsole();
        synchronized (Dispatcher.LOCK_CONSOLE) {
            cashiersWorkToConsole(buyer, s);
        }
        int time = RandomHelper.random(2000, 5000);
        RandomHelper.sleep(buyer.pensioneer ? (int) (time * 1.5) : time);
        synchronized (Buyer.getMonitor(buyer)) {
            buyer.notify();
        }
    }

    //кассы с определённым покупателем в консоль
    private void cashiersWorkToConsole(Buyer buyer, String s) {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSize of queue: " +
                QueueBuyer.getQueueSize());
        System.out.println(s + this + " service " + buyer);
        double price = 0;
        for (Map.Entry<String, Double> entry : buyer.basket.goods.entrySet()) {
            System.out.println(s + buyer + " bought " + entry.getKey() + " for " + entry.getValue());
            price += entry.getValue();
        }
        price = (int) Math.round(price * 10) / 10.0;
        System.out.println(s + buyer + " totally paid = " + price);
        Dispatcher.increaseOverallPrice(price);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMarket's cashier: " +
                Dispatcher.getOverallPrice());
    }

    //в консоль результатов работы определённой кассы
    private String getShiftForConsole() {
        String s;
        switch (this.numberOfCashier) {
            case 1:
                s = "\t\t\t";
                break;
            case 2:
                s = "\t\t\t\t\t\t";
                break;
            case 3:
                s = "\t\t\t\t\t\t\t\t\t";
                break;
            case 4:
                s = "\t\t\t\t\t\t\t\t\t\t\t\t";
                break;
            case 5:
                s = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                break;
            case 6:
                s = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                break;
            case 7:
                s = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                break;
            case 8:
                s = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                break;
            default:
                s = "";
        }
        return s;
    }

    //нужны ли дополнительные кассы
    private void startMoreCashiers() {
        if (Dispatcher.getCountCashiers() < 5) {
            while (QueueBuyer.getQueueSize() > Dispatcher.getCountCashiers() * 5) {
                Thread th = new Thread(new Cashier(Dispatcher.cashiersNumber++));
                synchronized (Dispatcher.LOCK_ARRAY) {
                    Market.listOfThreads.add(th);
                }
                th.start();
            }
        }
    }

    //сохранение результатов cashierLog.txt
    private void logCashier(Buyer buyer) {
        String path = RandomHelper.getCashierLogPath();
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            switch (numberOfCashier) {
                case 1:
                    logCashier1(buyer, pw);
                    break;
                case 2:
                    logCashier2(buyer, pw);
                    break;
                case 3:
                    logCashier3(buyer, pw);
                    break;
                case 4:
                    logCashier4(buyer, pw);
                    break;
                case 5:
                    logCashier5(buyer, pw);
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //работа первой кассы cashierLog.txt
    private void logCashier1(Buyer buyer, PrintWriter pw) {
        pw.printf("%-90s%s\n",
                "C1 service B" + buyer.getBuyerNumber(), QueueBuyer.getQueueSize());
        double res = 0;
        for (Map.Entry<String, Double> entry : buyer.basket.goods.entrySet()) {
            String operation = String.format("%9s - %3.1f", entry.getKey(), entry.getValue());
            pw.printf("%s\n", operation);
            res += entry.getValue();
        }
        String resOperation = String.format("%9s - %3.1f", "overall", res);
        pw.printf("%-90s%-18s%.1f\n", resOperation, "", Dispatcher.getOverallPrice());
        pw.println("----------------------------------------------------------" +
                "------------------------------------------------------------");
    }

    //работа второй кассы - cashierLog.txt
    private void logCashier2(Buyer buyer, PrintWriter pw) {
        pw.printf("%-18s%-72s%s\n", "",
                "C2 service B" + buyer.getBuyerNumber(), QueueBuyer.getQueueSize());
        double res = 0;
        for (Map.Entry<String, Double> entry : buyer.basket.goods.entrySet()) {
            String operation = String.format("%9s - %3.1f", entry.getKey(), entry.getValue());
            pw.printf("%-18s%s\n", "", operation);
            res += entry.getValue();
        }
        String resOperation = String.format("%9s - %3.1f", "overall", res);
        pw.printf("%-18s%-72s%-18s%.1f\n", "", resOperation, "", Dispatcher.getOverallPrice());
        pw.println("----------------------------------------------------------" +
                "------------------------------------------------------------");
    }

    //работа третьей кассы -cashierLog.txt
    private void logCashier3(Buyer buyer, PrintWriter pw) {
        pw.printf("%-36s%-54s%s\n", "",
                "C3 service B" + buyer.getBuyerNumber(), QueueBuyer.getQueueSize());
        double res = 0;
        for (Map.Entry<String, Double> entry : buyer.basket.goods.entrySet()) {
            String operation = String.format("%9s - %3.1f", entry.getKey(), entry.getValue());
            pw.printf("%-36s%s\n", "", operation);
            res += entry.getValue();
        }
        String resOperation = String.format("%9s - %3.1f", "overall", res);
        pw.printf("%-36s%-54s%-18s%.1f\n", "", resOperation, "", Dispatcher.getOverallPrice());
        pw.println("----------------------------------------------------------" +
                "------------------------------------------------------------");
    }

    //работа четвёртой кассы cashierLog.txt
    private void logCashier4(Buyer buyer, PrintWriter pw) {
        pw.printf("%-54s%-36s%s\n", "",
                "C4 service B" + buyer.getBuyerNumber(), QueueBuyer.getQueueSize());
        double res = 0;
        for (Map.Entry<String, Double> entry : buyer.basket.goods.entrySet()) {
            String operation = String.format("%9s - %3.1f", entry.getKey(), entry.getValue());
            pw.printf("%-54s%s\n", "", operation);
            res += entry.getValue();
        }
        String resOperation = String.format("%9s - %3.1f", "overall", res);
        pw.printf("%-54s%-36s%-18s%.1f\n", "", resOperation, "", Dispatcher.getOverallPrice());
        pw.println("----------------------------------------------------------" +
                "------------------------------------------------------------");
    }

    //работа пятой кассы cashierLog.txt
    private void logCashier5(Buyer buyer, PrintWriter pw) {
        pw.printf("%-72s%-18s%s\n", "",
                "C5 service B" + buyer.getBuyerNumber(), QueueBuyer.getQueueSize());
        double res = 0;
        for (Map.Entry<String, Double> entry : buyer.basket.goods.entrySet()) {
            String operation = String.format("%9s - %3.1f", entry.getKey(), entry.getValue());
            pw.printf("%-72s%s\n", "", operation);
            res += entry.getValue();
        }
        String resOperation = String.format("%9s - %3.1f", "overall", res);
        pw.printf("%-72s%-18s%-18s%.1f\n", "", resOperation, "", Dispatcher.getOverallPrice());
        pw.println("----------------------------------------------------------" +
                "------------------------------------------------------------");
    }

    @Override
    public String toString() {
        return this.name;
    }
}