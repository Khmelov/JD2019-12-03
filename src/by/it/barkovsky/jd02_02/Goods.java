package by.it.barkovsky.jd02_02;

import java.util.HashMap;
import java.util.Map;

public class Goods {
    static Map<String, Double> priceList = new HashMap<>();

    static void loadPriceList() {
        priceList.put("product 1", 1.0);
        priceList.put("product 2", 1.5);
        priceList.put("product 3", 2.1);
        priceList.put("product 4", 9.0);
        priceList.put("product 5", 8.1);
        priceList.put("product 6", 7.1);
        priceList.put("product 7", 4.5);
        priceList.put("product 8", 7.5);
        priceList.put("product 9", 3.2);
        priceList.put("product 10", 3.1);
        priceList.put("product 11", 2.2);
    }
    //размера списка товаров магазина
    int getPriceListSize() {
        return priceList.size();
    }

    //цены товара из списка товаров магазина
    double getPrice(String goods) {
        if (priceList.containsKey(goods)){
           // System.out.printf("round(priceList.get(goods)*10/10.0 : %20d\n",Math.round(priceList.get(goods)*10/10.0) );
            return (int) Math.round(priceList.get(goods)*10/10.0);//Math.round(priceList.get(goods)*10)/10.0
        } else
            return 0.0;
    }
}
