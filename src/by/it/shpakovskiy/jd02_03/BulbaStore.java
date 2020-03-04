package by.it.shpakovskiy.jd02_03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

class BulbaStore {
    private final CopyOnWriteArraySet<Buyer> buyers;
    private final ConcurrentLinkedDeque<Basket> baskets;
    private final Showcase showcase;
    private final QueueBuyer queueBuyer;
    private AtomicInteger totalBuyersCount = new AtomicInteger(0);
    // Если true - магазин больше не пускает посетителей и закроется как только последний выйдет.
    private AtomicBoolean closing = new AtomicBoolean(false);
    private static final int CASHIERS_COUNT = 5;
    private ArrayList<Cashier> cashiers;
    private final Manager manager;
    private AtomicInteger revenue = new AtomicInteger(0);
    private static final Object outputMonitor = new Object();
    private final Semaphore tradeHall = new Semaphore(20, true);
    private final ExecutorService cashExecService;

    BulbaStore(int basketsCount) {
        buyers = new CopyOnWriteArraySet<>();
        baskets = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < basketsCount; i++) {
            baskets.add(new Basket());
        }
        showcase = new Showcase();
        queueBuyer = new QueueBuyer(30);
        cashiers = new ArrayList<>();
        cashExecService = Executors.newFixedThreadPool(CASHIERS_COUNT);
        for (int i = 0; i < CASHIERS_COUNT; i++) {
            Cashier cashier = new Cashier(i + 1, this);
            cashiers.add(cashier);
            cashExecService.execute(cashier);
        }
        cashExecService.shutdown();
        manager = new Manager(this);
        if (ShopRunner.IN_A_TABLE) printTitleLine();
    }
    //FIX. for compatibility with Java 8
    private static String repeat(String s, int n){
        return new String(new char[n]).replace("\0", s);
    }

    public int getTotalBuyersCount() {
        return totalBuyersCount.get();
    }

    public double getRevenue() {
        return revenue.doubleValue() / 100;
    }

    private double setRevenue(double revenue) {
        return (double) this.revenue.addAndGet((int) (revenue * 100)) / 100;
    }

    public Showcase getAccessToTheShowcase() {
        return showcase;
    }

    int enter(Buyer buyer) {
        if (!closing.get()) {
            buyers.add(buyer);
            if (totalBuyersCount.incrementAndGet() >= ShopRunner.MAX_COUNT) closing.set(true);
            return buyers.size();
        } else return -1;
    }

    void enterToTradeHall(Buyer buyer) {
        try {
            tradeHall.acquire();
        } catch (InterruptedException e) {
            System.err.println("InterruptedException " + e.getMessage());
        }
        if (!ShopRunner.IN_A_TABLE) System.out.println(buyer + " entered the trading hall.");
    }

    void leaveTradeHall(Buyer buyer) {
        tradeHall.release();
        if (!ShopRunner.IN_A_TABLE) System.out.println(buyer + " left the trading hall.");
    }

    boolean isClosedStore() {
        return buyers.size() == 0;
    }

    int leave(Buyer buyer) {
        buyers.remove(buyer);
        return buyers.size();
    }

    void freePersonal(Manager manager) {
        if (manager == this.manager) {
            for (Cashier cashier : cashiers) {
                cashier.endOfWorkingDay();
            }
            try {
                cashExecService.awaitTermination(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException " + e.getMessage());
            }
            if (!cashExecService.isTerminated())
                cashExecService.shutdownNow();
            this.manager.endOfWorkingDay();
        }
    }

    public int buyersCount() {
        return buyers.size();
    }

    int addToQueue(Buyer buyer) {
        if (!manager.isAlive()) {
            synchronized (manager) {
                manager.notify();
            }
        }
        return queueBuyer.addBuyer(buyer);
    }

    Buyer getBuyer() {
        return queueBuyer.next();
    }

    int queueLength() {
        return queueBuyer.length();
    }

    Basket getBasket() {
        return baskets.pollLast();
    }

    boolean isBasket() {
        return !baskets.isEmpty();
    }

    void returnBasket(Basket basket) {
        baskets.addLast(basket);
    }

    private int getNeededCashiersCount(int c) {
        if (c > 20) return 5;
        if (c > 15) return 4;
        if (c > 10) return 3;
        if (c > 5) return 2;
        if (c > 0) return 1;
        return 0;
    }

    private int[] getRandomArray(int length) {
        List<Integer> list = new ArrayList<>(length);
        boolean add;
        while (list.size() < length) {
            add = false;
            int v = (int) (Math.random() * length);
            while (!add) {
                if (!list.contains(v)) {
                    list.add(v);
                    add = true;
                } else {
                    v++;
                    if (v >= length) v = 0;
                }
            }
        }
        int[] r = new int[length];
        for (int i = 0; i < length; i++) {
            r[i] = list.get(i);
        }
        return r;
    }

    // Метод смотрит сколько людей в очереди, сколько нужно кассиров, и пробуждает отдыхающих
    // кассиров в случайном порядке пока их не станет столько сколько нужно
    void checkForCashiers(Manager manager) {
        if (manager == this.manager) {
            int count = queueLength(); //покупателей в очереди
            int need = getNeededCashiersCount(count);//кассиров должно быть
            int working = 0;
            for (Cashier cashier : cashiers) {
                if (!cashier.isWaiting()) working++;
            }
            if (need > working) {
                int[] ran = getRandomArray(cashiers.size());
                for (int i = 0; i < cashiers.size(); i++) {
                    if (cashiers.get(ran[i]).wakeUp()) {
                        if (!ShopRunner.IN_A_TABLE) System.out.println("Manager opened " + cashiers.get(ran[i]));
                        if (++working == need) break;
                    }
                }
            }
        }
    }

    private static void printTitleLine() {
        int w = ShopRunner.CHAR_IN_COLUMN;
        StringBuilder sb = new StringBuilder();
        sb.append("┌").append(repeat("—",167)).append("┐\n");
        sb.append("|");
        for (int i = 0; i < CASHIERS_COUNT; i++) {
            sb.append(String.format("%" + (w - 3) + "s%d  ", "cashier N", i + 1)).append('│');
        }
        sb.append(String.format("%" + (w - 2) + "s  ", "count queue")).append('│');
        sb.append(String.format("%" + (w - 2) + "s  ", "revenue")).append('│');
        sb.append("\n").append("├").append(repeat("—",167)).append("┤");
        System.out.println(sb);
    }

    void check(Buyer buyer, Cashier cashier, int lineLength) {
        Basket basket = buyer.getGoods();
        Product product /*= basket.getProduct()*/;
        List<String> output = new ArrayList<>();
        double fullSum = 0;
        while ((product= basket.getProduct()) != null) {
            double money = showcase.getPrice(product);
            if (!ShopRunner.IN_A_TABLE)
                System.out.println(cashier + " get from " + buyer + " $" + money + " per " + product);
            else output.add(getTableLine(cashier, product.toString(), money, lineLength, false));
            fullSum += money;
//            product = basket.getProduct();
        }
        if (!ShopRunner.IN_A_TABLE) {
            System.out.println("Total " + buyer + " spent $" + fullSum + ".");
            setRevenue(fullSum);
        } else output.add(getTableLine(cashier, "Total:", fullSum, lineLength, true));
        synchronized (outputMonitor) {
            output.forEach(System.out::println);
        }
    }

    private String getTableLine(Cashier cashier, String product, Double money, int lineLength, boolean flag) {
        int n = cashier.getNumber() - 1;
        int w = ShopRunner.CHAR_IN_COLUMN;
        StringBuilder sb = new StringBuilder();
        sb.append("│");
        for (int i = 0; i < CASHIERS_COUNT; i++) {
            if (i == n) {
                sb.append(String.format(" %" + (w - 9) + "s %6.2f ", product, money)).append('│');
            } else {
                sb.append(ShopRunner.EMPTY_COL).append('│');
            }
        }

        if (flag) {
            sb.append(String.format(" buyers count: %" + (w - 15) + "d", lineLength)).append('│');
            sb.append(String.format(" Total: %" + (w - 9) + ".2f ", setRevenue(money))).append('│');
            sb.append("\n").append("├").append(repeat("—",167)).append("┤");
        } else {
            sb.append(ShopRunner.EMPTY_COL).append('│');
            sb.append(ShopRunner.EMPTY_COL).append('│');
        }
        return sb.toString();
    }
}
