package by.it.drozd.jd02_02;

interface IBuyer {
    void enterToMarket();    //вошел в магазин (мгновенно)
    void chooseGoods();      //выбрал товар (от 0,5 до 2 секунд)
    void goToQueue();        //отправился  очередь
    void goOut();            //отправился на выход(мгновенно)
}
