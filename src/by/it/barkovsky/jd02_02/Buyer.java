package by.it.barkovsky.jd02_02;

public class Buyer extends Thread implements IBuyer {

    boolean pensioneer = false;
    Basket basket = new Basket();
    private int buyerNumber;     //номер покупателя

    public Buyer(int number){
        super("Buyer №:"+number);
        this.buyerNumber = number;
        Dispatcher.newBuyer();
        if (RandomHelper.random(1, 4) == 1) {
            this.pensioneer = true;
            this.setName(this.getName() + " (pensioneer)");
        }
    }

    @Override
    public void run() {
        enterToMarket();
        takeBasket();
        Goods goods = new Goods();
        int priceListSize = goods.getPriceListSize();
        int quantity = RandomHelper.random(1, 4);
        for (int i = 0; i < quantity; i++) {
            chooseGoods();
            putGoodsToBasket(goods, priceListSize);
        }
        goToQueue();
        goOut();
        Dispatcher.buyerLeaved(this);
    }
    //метод для кастования buyer'a к object'у
    static Object getMonitor(Buyer buyer) {
        return buyer;
    }

    //геттер для номера покупателя
    int getBuyerNumber() {
        return buyerNumber;
    }

    //покупатель отправляется в очередь
    private void goToQueue() {
        synchronized (Dispatcher.LOCK_CONSOLE) {
            System.out.println(this + " went to the Queue");
        }
        synchronized (this) {
            try {
                QueueBuyer.putToQueue(this);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void enterToMarket() {
        synchronized (Dispatcher.LOCK_CONSOLE) {
            System.out.println(this.getName() + " entered the market");
        }
    }

    @Override
    public void chooseGoods() {
        int timeout = RandomHelper.random(500, 2000);
        synchronized (Dispatcher.LOCK_CONSOLE) {
            System.out.println(this.getName() + " is choosing goods " + timeout + " milliseconds");
        }
        RandomHelper.sleep(pensioneer ? (int) (timeout * 1.5) : timeout);
    }

    @Override
    public void goOut() {
        synchronized (Dispatcher.LOCK_CONSOLE) {
            System.out.println(this.getName() + " exited the market");
        }
    }

    @Override
    public void takeBasket() {
        synchronized (Dispatcher.LOCK_CONSOLE) {
            System.out.println(this.getName() + " took basket");
        }
        int timeToTakeBasket = RandomHelper.random(100, 200);
        RandomHelper.sleep(pensioneer ? (int) (timeToTakeBasket * 1.5) : timeToTakeBasket);
    }

    @Override
    public void putGoodsToBasket(Goods list_of_goods, int priceListSize) {
        String goods = RandomHelper.getRandomGoods(priceListSize);
        synchronized (Dispatcher.LOCK_CONSOLE) {
            System.out.println(this.getName() + " took " + goods);
        }
        int timeToTakeGoods = RandomHelper.random(100, 200);
        RandomHelper.sleep(pensioneer ? (int) (timeToTakeGoods * 1.5) : timeToTakeGoods);
        double price = list_of_goods.getPrice(goods);
        this.basket.goods.put(goods, price);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
