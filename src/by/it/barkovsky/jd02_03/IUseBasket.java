package by.it.barkovsky.jd02_03;

public interface IUseBasket {
    void takeBacket() throws InterruptedException;
    void putGoodsToBasket(Goods goods, int listSize) throws InterruptedException;
}
