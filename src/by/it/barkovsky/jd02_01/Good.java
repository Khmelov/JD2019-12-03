package by.it.barkovsky.jd02_01;

import java.util.HashMap;
import java.util.Map;

public class Good {
    static Map<String, Double> priceList = new HashMap<>();

    static void loadPriceList() {
        priceList.put("product 1", 0.7);
        priceList.put("product 2", 1.3);
        priceList.put("product 3", 1.4);
        priceList.put("product 4", 4.0);
        priceList.put("product 5", 3.1);
        priceList.put("product 6", 2.1);
        priceList.put("product 7", 3.4);
        priceList.put("product 8", 0.6);
        priceList.put("product 9", 2.2);
        priceList.put("product 10", 3.1);
        priceList.put("product 11", 2.2);
    }

    static Map<String, Double> getListOfGoods() {
        return priceList;
    }
}