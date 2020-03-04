package by.it.barkovsky.jd02_01;

import java.util.*;

class Buyer extends Thread implements IBuyer, IUseBasket{

    Buyer(int number){
        super("Buyer № "+number);
        Dispatcher.count++;
    }

    @Override
    public void run() {
        enterToMarket();
        takeBasket();
        chooseGoods();
        putGoodsToBasket();
        goOut();
    }

    @Override
    public void enterToMarket() {
        System.out.println(this + " entered to the Market");
    }

    @Override
    public void chooseGoods() {
        System.out.println(this + " started to choose the goods");
        Helper.sleep(Helper.random(500, 2000));
        System.out.println(this + " finished choosing the goods");
    }


    @Override
    public void goOut() {
        System.out.println(this + " went out from Market");
        Dispatcher.count--;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public void takeBasket() {
        System.out.println(this+" take the basket.");
        Helper.sleep(Helper.random(500,2000));
    }

    @Override
    public void putGoodsToBasket() {
        System.out.println(this+" put the Goods into the basket");
        Helper.sleep(Helper.random(500,2000));
        putTo();
    }

    private void putTo() {
        int numberOfGoods = Helper.random(1,4);
        StringBuilder sb = new StringBuilder();
        List<String> namesOfGoods = new ArrayList<>(Good.getListOfGoods().keySet());
        for (int i = 1; i <= numberOfGoods; i++) {
            String randomGood = namesOfGoods.get(new Random().nextInt(namesOfGoods.size()));
            Double priceOfGood = Good.getListOfGoods().get(randomGood);
            sb.append(randomGood).append(" (").append(priceOfGood).append(" USD)");
            if(i == numberOfGoods)
                sb.append(".");
            else sb.append(",");
        }
        System.out.println(this + " bought the next goods:");
        System.out.println(sb.toString());
    }
}