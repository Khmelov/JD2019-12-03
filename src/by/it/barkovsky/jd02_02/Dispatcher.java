package by.it.barkovsky.jd02_02;

import java.util.HashMap;
import java.util.Map;

public class Dispatcher {static final int K_SPEED = 50;     //для ускорения программы
    static volatile int cashiersNumber = 1;                 //для кассиров
    private static volatile int countCashiers;              //кол-во одновременно работающих касс
    private static volatile int buyersCount;                //кол-во покупателей в магазине
    private static final Object LOCK_BUYERS = new Object(); //монитор для инкремента и декремента количества покупателей
    private static final Object LOCK_OVERALL_PRICE = new Object();     //монитор для суммарного оборота магазина
    static final Object LOCK_QUEUE = new Object();            //монитор для связи покупатель-кассир
    static final Object LOCK_CONSOLE = new Object();            //монитор для корректного вывода в консоль
    private static final Object LOCK_CASHIERS = new Object();    //монитор для инкремента номера создаваемой кассы
    static final Object LOCK_ARRAY = new Object();              //монитор для предотвращения изменения массива во время итерации
    private static final int plan = 100;                        //план работы магазина
    private static volatile double overallPrice;                //общий оборот магазина
    private static Map<Buyer, Double> listOfBuyers = new HashMap<>();//список всех покупателей побывавших в магазине

    //покупатель вошёл в магазин
    static void newBuyer() {
        synchronized (LOCK_BUYERS) {
            buyersCount++;
        }
    }

    //покупатель вышел из магазина
    static void buyerLeaved(Buyer buyer) {
        double price = 0;
        for (Double value : buyer.basket.goods.values()) {
            price += value;
        }
        synchronized (LOCK_BUYERS) {
            listOfBuyers.put(buyer, price);
            buyersCount--;
        }
    }

    //выполнен ли план по количеству покупателей
    static boolean planIsComplete() {
        synchronized (LOCK_BUYERS) {
            return listOfBuyers.size() == plan;
        }
    }

    /* открыт ли магазин (магазин закрывается когда общее кол-во покупателей внутри
    /* и прошедших через магазин больше плана)*/
    static boolean marketIsOpen() {
        synchronized (LOCK_BUYERS) {
            return listOfBuyers.size() + buyersCount < plan;
        }
    }

    //общий оборота магазина
    static double getOverallPrice() {
        return (int) Math.round(overallPrice * 10) / 10.0;
    }

    //чека покупателя в общем обороте магазина
    static void increaseOverallPrice(double res) {
        synchronized (LOCK_OVERALL_PRICE) {
            overallPrice += res;
        }
    }

    //кол-во работающих касс
    static int getCountCashiers() {
        synchronized (LOCK_CASHIERS){
            return countCashiers;
        }
    }

    //новая касса
    static void newCashier() {
        synchronized (Dispatcher.LOCK_CASHIERS) {
            Dispatcher.countCashiers++;
        }
    }

    //касса закрыта
    static void cashierCloses() {
        synchronized (Dispatcher.LOCK_CASHIERS) {
            Dispatcher.countCashiers--;
        }
    }

    //кол-во покупателей в магазине
    static int getBuyersCount() {
        return buyersCount;
    }
}