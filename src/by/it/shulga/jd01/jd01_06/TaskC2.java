package by.it.shulga.jd01.jd01_06;

import java.util.Random;

public class TaskC2 {
    public static void main(String[] args) {
        String myString = MyText.text;

        long time1 = System.nanoTime();
        System.out.println(slow(myString)+"\n");
        long time2 = System.nanoTime();

        long timeF1 = System.nanoTime();
        System.out.println(fast(myString)+"\n");
        long timeF2 = System.nanoTime();

        System.out.println("Slow Result = " + (time2 - time1)/1000);
        System.out.println("Slow Result = " + (timeF2 - timeF1)/1000);
    }

    private static String slow(String text) {
        String[] myArray = text.split("[^A-Za-zа-яА-ЯёЁ]+");
        String result = "";
        Random r1 = new Random(0);
        while (result.length() < 100000) {
//            int randomInt = (int)((myArray.length * Math.random())-1);  ВОПРОССС!!!!
            result += myArray[r1.nextInt(myArray.length)] + " ";
        }
        return result.trim();
    }

    private static String fast(String text) {
        String[] myArray = text.split("[^A-Za-zа-яА-ЯёЁ]+");
        StringBuilder sb1 = new StringBuilder();
        Random rnd = new Random(0);
        while (sb1.length() < 100000) {
            sb1.append(myArray[rnd.nextInt(myArray.length)]).append(" ");
        }
        return sb1.toString().trim();
    }
}
